package dik.library.rest;

import dik.library.model.Author;
import dik.library.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
//@RequestMapping(AuthorRestController.REST_URL)
@Api(value="REST API для работы с авторами", description = "REST API для работы с авторами")
public class AuthorRestController {
    //static final String REST_URL = "/rest/author";

    @Autowired
    AuthorService authorService;

    @ApiOperation(value = "Показать всех авторов")
    @GetMapping(value = "/rest/author", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Author> getAll() {
        return authorService.getAllAuthor();
    }

    @ApiOperation(value = "Показать автора")
    @GetMapping(value = "/rest/author/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Author get(@PathVariable("id") String id) {
        return authorService.getById(id);
    }

    @ApiOperation(value = "Добавить автора")
    @PostMapping(value = "/rest/author", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody Author author) {
        authorService.insert(author.getFirstName(), author.getSecondName());
    }

    @ApiOperation(value = "Удалить автора")
    @DeleteMapping(value = "/rest/author/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        authorService.deleteById(id);
    }

    @ApiOperation(value = "Изменить автора")
    @PutMapping(value = "/rest/author/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Author author, @PathVariable("id") String id) {
        author.setId(id);
        authorService.update(author);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleNoAuthorDeleteWithBooks(RuntimeException ex) {
        return ResponseEntity.badRequest().body("Удалите сначала книги");
    }

    @ExceptionHandler({IOException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<String> handleAuthorNotFound() {
        return ResponseEntity.badRequest().body("Автор не найден");
    }

}
