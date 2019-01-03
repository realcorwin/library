package dik.library.service;

import dik.library.model.Genre;

import java.util.List;

public interface GenreService {
    int count();
    Genre getById(int id);
    List<Genre> getAllGenre();
    void insert(int id, String genreName);
    void deleteById(int id);
}
