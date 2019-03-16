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
    DataSource dataSource;

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    AuthorRepository authorRepositoryH2;

    @Autowired
    GenreRepository genreRepositoryH2;

    @Bean
    public MongoItemReader<Book> reader() {
        MongoItemReader<Book> mongoItemReader = new MongoItemReader<>();
        mongoItemReader.setName("bookReader");
        mongoItemReader.setTargetType(Book.class);
        mongoItemReader.setTemplate(mongoOperations);
        mongoItemReader.setQuery("{}");
        mongoItemReader.setSort(new HashMap<String, Sort.Direction>() {{
            put("_id", Sort.Direction.DESC);
        }});
        return mongoItemReader;
    }

    @Bean
    public EmbeddedH2ItemWriter writer() {
        return new EmbeddedH2ItemWriter();
    }

    @Bean
    public ItemProcessor processor() {
        return (ItemProcessor<Book, dik.library.entity.Book>) book -> {
            dik.library.entity.Book bookH2 = new dik.library.entity.Book();
            bookH2.setName(book.getName());
            bookH2.setDescription(book.getDescription());
            Author authorH2 = new Author();
            authorH2.setFirstName(book.getAuthor().getFirstName());
            authorH2.setSecondName(book.getAuthor().getSecondName());
            bookH2.setAuthor(authorH2);
            authorRepositoryH2.save(authorH2);
            Genre genreH2 = new Genre();
            genreH2.setGenreName(book.getGenre().getGenreName());
            bookH2.setGenre(genreH2);
            genreRepositoryH2.save(genreH2);
            return bookH2;
        };
    }

    @Bean
    public Job importUserJob(Step step1) {
        return jobBuilderFactory.get("bookReader")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
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

    @Bean
    public Step step1(EmbeddedH2ItemWriter writer) {
        return stepBuilderFactory.get("step1")
                .chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .listener(new ItemReadListener() {
                    public void beforeRead() { System.out.println("Начало чтения"); }
                    public void afterRead(Object o) { System.out.println("Конец чтения"); }
                    public void onReadError(Exception e) { System.out.println("Ошибка чтения"); }
                })
                .listener(new ItemWriteListener() {
                    public void beforeWrite(List list) { System.out.println("Начало записи"); }
                    public void afterWrite(List list) { System.out.println("Конец записи"); }
                    public void onWriteError(Exception e, List list) { System.out.println("Ошибка записи"); }
                })
                .listener(new ItemProcessListener() {
                    public void beforeProcess(Object o) {System.out.println("Начало обработки");}
                    public void afterProcess(Object o, Object o2) {System.out.println("Конец обработки");}
                    public void onProcessError(Object o, Exception e) {System.out.println("Ошибка обработки");}
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {System.out.println("Начало пачки");}
                    public void afterChunk(ChunkContext chunkContext) {System.out.println("Конец пачки");}
                    public void afterChunkError(ChunkContext chunkContext) {System.out.println("Ошибка пачки");}
                })
                .build();
    }
}
