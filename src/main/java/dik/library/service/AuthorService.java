package dik.library.service;

import dik.library.model.Author;

import java.util.List;

public interface AuthorService {
    long count();
    Author getById(long id);
    List<Author> getAllAuthor();
    void insert(String firstName, String secondName);
    void deleteById(long id);
}
