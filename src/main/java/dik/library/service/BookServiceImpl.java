package dik.library.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import dik.library.model.Book;
import dik.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    @HystrixCommand(groupKey = "bookService", commandKey = "getBook")
    public Book getById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    @HystrixCommand(groupKey = "bookService", commandKey = "getAllBook")
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    @HystrixCommand(groupKey = "bookService", commandKey = "insertBook")
    public void insert(Book book) {
        bookRepository.save(book);
    }

    @Override
    @HystrixCommand(groupKey = "bookService", commandKey = "deleteBook")
    public void deleteById(String id) {
        bookRepository.deleteByIdWithComments(id);
    }

    @Override
    @HystrixCommand(groupKey = "bookService", commandKey = "updateBook")
    public Book update(Book book) {
        return bookRepository.save(book);
    }
}
