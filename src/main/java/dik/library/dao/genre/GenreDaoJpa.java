package dik.library.dao.genre;

import dik.library.model.Genre;

import java.util.List;

public interface GenreDaoJpa {
    long count();
    Genre getById(long id);
    List<Genre> getAllGenre();
    void deleteById(long id);
    void insert(Genre genre);
}
