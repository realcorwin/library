package dik.library.dao.book;

import dik.library.model.Book;

import java.util.List;

public interface BookDao {
    int count();
    Book getById(int id);
    List<Book> getAllBook();
    void deleteById(int id);
    void insert(Book book);
}
