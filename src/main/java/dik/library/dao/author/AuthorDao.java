package dik.library.dao.author;

import dik.library.model.Author;

import java.util.List;

public interface AuthorDao {
    int count();
    Author getById(int id);
    List<Author> getAllAuthor();
    void deleteById(int id);
    void insert(Author author);
}
