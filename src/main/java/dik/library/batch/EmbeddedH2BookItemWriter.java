package dik.library.batch;

import dik.library.entity.Book;
import dik.library.repository.BookRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmbeddedH2BookItemWriter implements ItemWriter<Book> {

    @Autowired
    private BookRepository bookRepositoryH2;

    @Override
    public void write(List<? extends Book> books) throws Exception {
        bookRepositoryH2.saveAll(books);
    }
}