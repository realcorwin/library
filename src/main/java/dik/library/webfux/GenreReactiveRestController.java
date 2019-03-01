package dik.library.webfux;

import dik.library.model.Genre;
import dik.library.reactiverepo.GenreReactiveRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@Api(value="Reactive REST API для работы с жанрами", description = "Reactive REST API для работы с жанрами")
public class GenreReactiveRestController {

    @Autowired
    GenreReactiveRepository genreReactiveRepository;

    @ApiOperation(value = "Показать все жанры")
    @GetMapping(value = "/reactive/genre", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Genre> getAll() {
        return genreReactiveRepository.findAll();
    }

    @ApiOperation(value = "Показать жанр")
    @GetMapping(value = "/reactive/genre/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Genre> get(@PathVariable("id") String id) {
        return genreReactiveRepository.findById(id);
    }

    @ApiOperation(value = "Добавить жанр")
    @PostMapping(value = "/reactive/genre", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> add(@RequestBody Genre genre) {
        return genreReactiveRepository.save(new Genre(genre.getGenreName())).then();
    }

    @ApiOperation(value = "Удалить жанр")
    @DeleteMapping(value = "/reactive/genre/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable("id") String id) {
        return genreReactiveRepository.deleteByIdOnlyIfNotInBooks(id).then();
    }

    @ApiOperation(value = "Изменить жанр")
    @PutMapping(value = "/reactive/genre/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> update(@RequestBody Genre genre, @PathVariable("id") String id) {
        genre.setId(id);
        return genreReactiveRepository.save(genre).then();
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
