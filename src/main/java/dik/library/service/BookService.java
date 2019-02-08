package dik.library.service;

import dik.library.model.Book;

import java.util.List;

public interface BookService {
    long count();
    Book getById(String id);
    List<Book> getAllBook();
    void insert(Book book);
    void deleteById(String id);
    Book update(Book book);
}
