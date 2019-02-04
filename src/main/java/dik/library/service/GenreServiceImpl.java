package dik.library.service;

import dik.library.model.Genre;
import dik.library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public long count() {
        return genreRepository.count();
    }

    @Override
    public Genre getById(String id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Override
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    @Override
    public void insert(String genreName) {
        genreRepository.save(new Genre(genreName));
    }

    @Override
    public void deleteById(String id) {
        genreRepository.deleteById(id);
    }

    @Override
    public void update(Genre genre) {
        genreRepository.save(genre);
    }
}
