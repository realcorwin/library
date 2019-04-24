package dik.library.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand(groupKey = "genreService", commandKey = "getGenre")
    public Genre getById(String id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Override
    @HystrixCommand(groupKey = "genreService", commandKey = "getAllGenre")
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    @Override
    @HystrixCommand(groupKey = "genreService", commandKey = "insertGenre")
    public void insert(String genreName) {
        genreRepository.save(new Genre(genreName));
    }

    @Override
    @HystrixCommand(groupKey = "genreService", commandKey = "deleteGenre")
    public void deleteById(String id) {
        genreRepository.deleteById(id);
    }

    @Override
    @HystrixCommand(groupKey = "genreService", commandKey = "updateGenre")
    public void update(Genre genre) {
        genreRepository.save(genre);
    }
}
