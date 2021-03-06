package dik.library.service;

import dik.library.model.Author;
import dik.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public long count() {
        return authorRepository.count();
    }

    @Override
    public Author getById(String id) {
        return authorRepository.findById(id).orElse(null);
        // return authorRepository.getOne(id); //no sessions
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    @Override
    public void insert(String firstName, String secondName) {
        authorRepository.save(new Author(firstName, secondName));
    }

    @Override
    public void deleteById(String id) {
        authorRepository.deleteById(id);
    }

    @Override
    public void update(Author author) {
        authorRepository.save(author);
    }
}
