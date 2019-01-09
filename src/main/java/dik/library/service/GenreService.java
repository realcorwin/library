package dik.library.service;

import dik.library.model.Genre;

import java.util.List;

public interface GenreService {
    int count();
    Genre getById(long id);
    List<Genre> getAllGenre();
    void insert(long id, String genreName);
    void deleteById(long id);
}
