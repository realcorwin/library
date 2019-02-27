package dik.library.webfux;

import dik.library.model.Author;
import dik.library.reactiverepo.AuthorReactiveRepository;
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
@Api(value="Reactive REST API для работы с авторами", description = "Reactive REST API для работы с авторами")
public class AuthorReactiveRestController {

    @Autowired
    AuthorReactiveRepository authorReactiveRepository;

    @ApiOperation(value = "Показать всех авторов")
    @GetMapping(value = "/reactive/author", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Author> getAll() {
        return authorReactiveRepository.findAll();
    }

    @ApiOperation(value = "Показать автора")
    @GetMapping(value = "/reactive/author/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Author> get(@PathVariable("id") String id) {
        return authorReactiveRepository.findById(id);
    }

    @ApiOperation(value = "Добавить автора")
    @PostMapping(value = "/reactive/author", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> add(@RequestBody Author author) {
        return authorReactiveRepository.save(new Author(author.getFirstName(), author.getSecondName())).then();
    }

    @ApiOperation(value = "Удалить автора")
    @DeleteMapping(value = "/reactive/author/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable("id") String id) {
        return authorReactiveRepository.deleteByIdOnlyIfNotInBooks(id).then();
    }

    @ApiOperation(value = "Изменить автора")
    @PutMapping(value = "/reactive/author/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> update(@RequestBody Author author, @PathVariable("id") String id) {
        author.setId(id);
        return authorReactiveRepository.save(author).then();
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
