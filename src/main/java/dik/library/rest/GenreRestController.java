package dik.library.rest;

import dik.library.model.Genre;
import dik.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
//@RequestMapping(GenreRestController.REST_URL)
public class GenreRestController {
    //static final String REST_URL = "/rest/genre";

    @Autowired
    GenreService genreService;

    @GetMapping(value = "/rest/genre", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Genre> getAll() {
        return genreService.getAllGenre();
    }

    @GetMapping(value = "/rest/genre/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Genre get(@PathVariable("id") String id) {
        return genreService.getById(id);
    }

    @PostMapping(value = "/rest/genre", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody Genre genre) {
        genreService.insert(genre.getGenreName());
    }

    @DeleteMapping(value = "/rest/genre/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        genreService.deleteById(id);
    }

    @PutMapping(value = "/rest/genre/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Genre genre, @PathVariable("id") String id) {
        genre.setId(id);
        genreService.update(genre);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleNoGenreDeleteWithBooks(RuntimeException ex) {
        return ResponseEntity.badRequest().body("Удалите сначала книги");
    }

    @ExceptionHandler({IOException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<String> handleGenreNotFound() {
        return ResponseEntity.badRequest().body("Жанр не найден");
    }
}
