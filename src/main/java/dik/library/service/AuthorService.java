package dik.library.service;

import dik.library.model.Author;

import java.util.List;

public interface AuthorService {
    int count();
    Author getById(int id);
    List<Author> getAllAuthor();
    void insert(int id, String firstName, String secondName);
    void deleteById(int id);
}
