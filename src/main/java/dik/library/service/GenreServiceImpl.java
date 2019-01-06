package dik.library.service;

import dik.library.dao.genre.GenreDaoJdbcImpl;
import dik.library.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDaoJdbcImpl genreDaoJdbc;

    @Autowired
    public GenreServiceImpl(GenreDaoJdbcImpl genreDaoJdbc) {
        this.genreDaoJdbc = genreDaoJdbc;
    }

    @Override
    public int count() {
        return genreDaoJdbc.count();
    }

    @Override
    public Genre getById(long id) {
        return genreDaoJdbc.getById(id);
    }

    @Override
    public List<Genre> getAllGenre() {
        return genreDaoJdbc.getAllGenre();
    }

    @Override
    public void insert(long id, String genreName) {
        genreDaoJdbc.insert(new Genre(id, genreName));
    }

    @Override
    public void deleteById(long id) {
        genreDaoJdbc.deleteById(id);
    }
}
