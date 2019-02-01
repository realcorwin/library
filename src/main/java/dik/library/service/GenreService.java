package dik.library.service;

import dik.library.model.Genre;

import java.util.List;

public interface GenreService {
    long count();
    Genre getById(String id);
    List<Genre> getAllGenre();
    void insert(String genreName);
    void deleteById(String id);
}
