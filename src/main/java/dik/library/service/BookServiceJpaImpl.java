package dik.library.service;

import dik.library.dao.book.BookDaoJpaImpl;
import dik.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceJpaImpl implements BookServiceJpa {

    final private BookDaoJpaImpl bookDaoJpa;

    @Autowired
    public BookServiceJpaImpl(BookDaoJpaImpl bookDaoJpa) {
        this.bookDaoJpa = bookDaoJpa;
    }

    @Override
    public int count() {
        return bookDaoJpa.count();
    }

    @Override
    public Book getById(long id) {
        return bookDaoJpa.getById(id);
    }

    @Override
    public List<Book> getAllBook() {
        return bookDaoJpa.getAllBook();
    }

    @Override
    public void insert(Book book) {
        bookDaoJpa.insert(book);
    }

    @Override
    public void deleteById(long id) {
        bookDaoJpa.deleteById(id);
    }
}
