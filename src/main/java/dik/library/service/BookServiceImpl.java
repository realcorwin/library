package dik.library.service;

import dik.library.dao.book.BookDaoJdbcImpl;
import dik.library.model.Author;
import dik.library.model.Book;
import dik.library.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDaoJdbcImpl bookDaoJdbc;

    @Autowired
    public BookServiceImpl(BookDaoJdbcImpl bookDaoJdbc) {
        this.bookDaoJdbc = bookDaoJdbc;
    }

    @Override
    public int count() {
        return bookDaoJdbc.count();
    }

    @Override
    public Book getById(long id) {
        return bookDaoJdbc.getById(id);
    }

    @Override
    public List<Book> getAllBook() {
        return bookDaoJdbc.getAllBook();
    }

    @Override
    public void insert(Book book) {
        bookDaoJdbc.insert(book);
    }

    @Override
    public void deleteById(long id) {
        bookDaoJdbc.deleteById(id);
    }
}
