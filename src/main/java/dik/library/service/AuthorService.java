package dik.library.service;

import dik.library.model.Author;

import java.util.List;

public interface AuthorService {
    int count();
    Author getById(long id);
    List<Author> getAllAuthor();
    void insert(long id, String firstName, String secondName);
    void deleteById(long id);
}
