package dik.library.service;

import dik.library.dao.author.AuthorDaoJdbcImpl;
import dik.library.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDaoJdbcImpl authorDaoJdbc;

    @Autowired
    public AuthorServiceImpl(AuthorDaoJdbcImpl authorDaoJdbc) {
        this.authorDaoJdbc = authorDaoJdbc;
    }

    @Override
    public int count() {
        return authorDaoJdbc.count();
    }

    @Override
    public Author getById(int id) {
        return authorDaoJdbc.getById(id);
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorDaoJdbc.getAllAuthor();
    }

    @Override
    public void insert(int id, String firstName, String secondName) {
        authorDaoJdbc.insert(new Author(id, firstName, secondName));
    }

    @Override
    public void deleteById(int id) {
        authorDaoJdbc.getById(id);
    }
}
