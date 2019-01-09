package dik.library.dao.author;

import dik.library.model.Author;

import java.util.List;

public interface AuthorDao {
    int count();
    Author getById(long id);
    List<Author> getAllAuthor();
    void deleteById(long id);
    void insert(Author author);
}
