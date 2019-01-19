package dik.library.service;

import dik.library.model.Genre;

import java.util.List;

public interface GenreServiceJpa {
    long count();
    Genre getById(long id);
    List<Genre> getAllGenre();
    void insert(String genreName);
    void deleteById(long id);
}
