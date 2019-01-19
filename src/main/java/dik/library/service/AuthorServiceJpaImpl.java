package dik.library.service;

import dik.library.dao.author.AuthorDaoJpaImpl;
import dik.library.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceJpaImpl implements AuthorServiceJpa {

    private final AuthorDaoJpaImpl authorDaoJpa;

    @Autowired
    public AuthorServiceJpaImpl(AuthorDaoJpaImpl authorDaoJpa) {
        this.authorDaoJpa = authorDaoJpa;
    }

    @Override
    public long count() {
        return authorDaoJpa.count();
    }

    @Override
    public Author getById(long id) {
        return authorDaoJpa.getById(id);
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorDaoJpa.getAllAuthor();
    }

    @Override
    public void insert(String firstName, String secondName) {
        authorDaoJpa.insert(new Author(firstName, secondName));
    }

    @Override
    public void deleteById(long id) {
        authorDaoJpa.deleteById(id);
    }
}
