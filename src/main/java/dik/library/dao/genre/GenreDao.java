package dik.library.dao.genre;

import dik.library.model.Genre;

import java.util.List;

public interface GenreDao {
    int count();
    Genre getById(int id);
    List<Genre> getAllGenre();
    void deleteById(int id);
    void insert(Genre genre);
}
