package dik.library.service;

import dik.library.model.Book;

import java.util.List;

public interface BookService {
    int count();
    Book getById(long id);
    List<Book> getAllBook();
    void insert(long id, String name, String description,
                long idAuthor, String firstName, String secondName,
                long idGenre, String genreName);
    void insert(Book book);
    void deleteById(long id);
}
