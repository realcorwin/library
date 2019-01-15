package dik.library.service;

import dik.library.model.Book;

import java.util.List;

public interface BookServiceJpa {
    int count();
    Book getById(long id);
    List<Book> getAllBook();
    void insert(Book book);
    void deleteById(long id);
}
