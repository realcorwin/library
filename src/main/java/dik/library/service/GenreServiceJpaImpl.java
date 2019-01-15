package dik.library.service;

import dik.library.dao.genre.GenreDaoJdbcImpl;
import dik.library.dao.genre.GenreDaoJpaImpl;
import dik.library.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceJpaImpl implements GenreServiceJpa {

    final private GenreDaoJpaImpl genreDaoJpa;

    @Autowired
    public GenreServiceJpaImpl(GenreDaoJpaImpl genreDaoJpa) {
        this.genreDaoJpa = genreDaoJpa;
    }

    @Override
    public int count() {
        return genreDaoJpa.count();
    }

    @Override
    public Genre getById(long id) {
        return genreDaoJpa.getById(id);
    }

    @Override
    public List<Genre> getAllGenre() {
        return genreDaoJpa.getAllGenre();
    }

    @Override
    public void insert(String genreName) {
        genreDaoJpa.insert(new Genre(genreName));
    }

    @Override
    public void deleteById(long id) {
        genreDaoJpa.deleteById(id);
    }
}
