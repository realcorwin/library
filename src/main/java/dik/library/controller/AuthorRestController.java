package dik.library.controller;

import dik.library.model.Author;
import dik.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AuthorRestController.REST_URL)
public class AuthorRestController {
    static final String REST_URL = "/rest/authors";

    @Autowired
    AuthorService authorService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Author> getAll() {
        return authorService.getAllAuthor();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Author get(@PathVariable("id") String id) {
        return authorService.getById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody Author author) {
        authorService.insert(author.getFirstName(), author.getSecondName());
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        authorService.deleteById(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Author author, @PathVariable("id") String id) {
        authorService.update(author);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleNoGenreDeleteWithBooks(RuntimeException ex) {
        return ResponseEntity.badRequest().body("Удалите сначала книги");
    }
}
