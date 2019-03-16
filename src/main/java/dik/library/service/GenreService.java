package dik.library.service;

import dik.library.entity.Genre;

import java.util.List;

public interface GenreService {
    long count();
    Genre getById(long id);
    List<Genre> getAllGenre();
    void insert(String genreName);
    void deleteById(long id);
}
