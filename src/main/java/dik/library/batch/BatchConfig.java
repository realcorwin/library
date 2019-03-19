package dik.library.batch;

import dik.library.entity.Author;
import dik.library.entity.Genre;
import dik.library.model.Book;
import dik.library.repository.AuthorRepository;
import dik.library.repository.GenreRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    AuthorRepository authorRepositoryH2;

    @Autowired
    GenreRepository genreRepositoryH2;

    @Bean
    public MongoItemReader<Book> bookReader() {
        MongoItemReader<Book> bookMongoItemReader = new MongoItemReader<>();
        bookMongoItemReader.setName("bookReader");
        bookMongoItemReader.setTargetType(Book.class);
        bookMongoItemReader.setTemplate(mongoOperations);
        bookMongoItemReader.setQuery("{}");
        bookMongoItemReader.setSort(new HashMap<String, Sort.Direction>() {{
            put("_id", Sort.Direction.DESC);
        }});
        return bookMongoItemReader;
    }

    @Bean
    public EmbeddedH2BookItemWriter bookWriter() {
        return new EmbeddedH2BookItemWriter();
    }

    @Bean
    public ItemProcessor bookProcessor() {
        return (ItemProcessor<Book, dik.library.entity.Book>) book -> {
            dik.library.entity.Book bookH2 = new dik.library.entity.Book();
            bookH2.setName(book.getName());
            bookH2.setDescription(book.getDescription());
            Author authorH2 = getOrCreateAuthor(book);
            bookH2.setAuthor(authorH2);
            authorRepositoryH2.save(authorH2);
            Genre genreH2 = getOrCreateGenre(book);
            bookH2.setGenre(genreH2);
            genreRepositoryH2.save(genreH2);
            return bookH2;
        };
    }

    private Genre getOrCreateGenre(Book book) {
        Genre genreH2;
        if (genreRepositoryH2.findFirstByGenreName(book.getGenre().getGenreName()) != null)
            genreH2 = genreRepositoryH2.findFirstByGenreName(book.getGenre().getGenreName());
        else {
            genreH2 = new Genre();
            genreH2.setGenreName(book.getGenre().getGenreName());
        }
        return genreH2;
    }

    private Author getOrCreateAuthor(Book book) {
        Author authorH2;
        if (authorRepositoryH2.findFirstByFirstNameAndSecondName(book.getAuthor().getFirstName(), book.getAuthor().getSecondName()) != null)
            authorH2 = authorRepositoryH2.findFirstByFirstNameAndSecondName(book.getAuthor().getFirstName(), book.getAuthor().getSecondName());
        else {
            authorH2 = new Author();
            authorH2.setFirstName(book.getAuthor().getFirstName());
            authorH2.setSecondName(book.getAuthor().getSecondName());
        }
        return authorH2;
    }

    @Bean
    public MongoItemReader<dik.library.model.Author> authorReader() {
        MongoItemReader<dik.library.model.Author> authorMongoItemReader = new MongoItemReader<>();
        authorMongoItemReader.setName("authorReader");
        authorMongoItemReader.setTargetType(dik.library.model.Author.class);
        authorMongoItemReader.setTemplate(mongoOperations);
        authorMongoItemReader.setQuery("{}");
        authorMongoItemReader.setSort(new HashMap<String, Sort.Direction>() {{
            put("_id", Sort.Direction.DESC);
        }});
        return authorMongoItemReader;
    }

    @Bean
    public EmbeddedH2AuthorItemWriter authorWriter() {
        return new EmbeddedH2AuthorItemWriter();
    }

    @Bean
    public ItemProcessor authorProcessor() {
        return (ItemProcessor<dik.library.model.Author, Author>) author -> {
            Author authorH2 = new Author();
            authorH2.setFirstName(author.getFirstName());
            authorH2.setSecondName(author.getSecondName());
            return authorH2;
        };
    }

    @Bean
    public MongoItemReader<dik.library.model.Genre> genreReader() {
        MongoItemReader<dik.library.model.Genre> genreMongoItemReader = new MongoItemReader<>();
        genreMongoItemReader.setName("genreReader");
        genreMongoItemReader.setTargetType(dik.library.model.Genre.class);
        genreMongoItemReader.setTemplate(mongoOperations);
        genreMongoItemReader.setQuery("{}");
        genreMongoItemReader.setSort(new HashMap<String, Sort.Direction>() {{
            put("_id", Sort.Direction.DESC);
        }});
        return genreMongoItemReader;
    }

    @Bean
    public EmbeddedH2GenreItemWriter genreWriter() {
        return new EmbeddedH2GenreItemWriter();
    }

    @Bean
    public ItemProcessor genreProcessor() {
        return (ItemProcessor<dik.library.model.Genre, Genre>) genre -> {
            Genre genreH2 = new Genre();
            genreH2.setGenreName(genre.getGenreName());
            return genreH2;
        };
    }

    @Bean
    public Job transferLibraryJob(Step authorTransfer, Step genreTransfer, Step bookTransfer) {
        return jobBuilderFactory.get("libraryTransfer")
                .incrementer(new RunIdIncrementer())
                .start(authorTransfer)
                .next(genreTransfer)
                .next(bookTransfer)
                //.end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        System.out.println("Начало job");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        System.out.println("Конец job");
                    }
                })
                .build();
    }

    private ItemReadListener readListener = new ItemReadListener() {
        @Override
        public void beforeRead() {
            System.out.println("Начало чтения");
        }

        @Override
        public void afterRead(Object o) {
            System.out.println("Конец чтения");
        }

        @Override
        public void onReadError(Exception e) {
            System.out.println("Ошибка чтения");
        }
    };

    private ItemWriteListener writeListener = new ItemWriteListener() {
        @Override
        public void beforeWrite(List list) {
            System.out.println("Начало записи");
        }

        @Override
        public void afterWrite(List list) {
            System.out.println("Конец записи");
        }

        @Override
        public void onWriteError(Exception e, List list) {
            System.out.println("Ошибка записи");
        }
    };

    private ItemProcessListener processListener = new ItemProcessListener() {
        @Override
        public void beforeProcess(Object o) {
            System.out.println("Начало обработки");
        }

        @Override
        public void afterProcess(Object o, Object o2) {
            System.out.println("Конец обработки");
        }

        @Override
        public void onProcessError(Object o, Exception e) {
            System.out.println("Ошибка обработки");
        }
    };

    private ChunkListener chunkListener = new ChunkListener() {
        @Override
        public void beforeChunk(ChunkContext chunkContext) {
            System.out.println("Начало пачки");
        }

        @Override
        public void afterChunk(ChunkContext chunkContext) {
            System.out.println("Конец пачки");
        }

        @Override
        public void afterChunkError(ChunkContext chunkContext) {
            System.out.println("Ошибка пачки");
        }
    };

    @Bean
    @SuppressWarnings("unchecked")
    public Step authorTransfer() {
        return stepBuilderFactory.get("authorTransfer")
                .chunk(5)
                .reader(authorReader())
                .processor(authorProcessor())
                .writer(authorWriter())
                .listener(readListener)
                .listener(writeListener)
                .listener(processListener)
                .listener(chunkListener)
                .build();
    }

    @Bean
    @SuppressWarnings("unchecked")
    public Step genreTransfer() {
        return stepBuilderFactory.get("genreTransfer")
                .chunk(5)
                .reader(genreReader())
                .processor(genreProcessor())
                .writer(genreWriter())
                .listener(readListener)
                .listener(writeListener)
                .listener(processListener)
                .listener(chunkListener)
                .build();
    }

    @Bean
    @SuppressWarnings("unchecked")
    public Step bookTransfer() {
        return stepBuilderFactory.get("bookTransfer")
                .chunk(5)
                .reader(bookReader())
                .processor(bookProcessor())
                .writer(bookWriter())
                .listener(readListener)
                .listener(writeListener)
                .listener(processListener)
                .listener(chunkListener)
                .build();
    }
}
