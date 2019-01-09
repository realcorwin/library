package dik.library.dao.book;

import dik.library.model.Book;

import java.util.List;

public interface BookDao {
    int count();
    Book getById(long id);
    List<Book> getAllBook();
    void deleteById(long id);
    void insert(Book book);
}
