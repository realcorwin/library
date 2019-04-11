package dik.library.webfux;

import dik.library.model.Book;
import dik.library.reactiverepo.BookReactiveRepository;
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
@Api(value="Reactive REST API для работы с книгами", description = "Reactive REST API для работы с книгами")
public class BookReactiveRestController {

    @Autowired
    BookReactiveRepository bookReactiveRepository;

    @ApiOperation(value = "Показать все книги")
    @GetMapping(value = "/reactive/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Book> getAll() {
        return bookReactiveRepository.findAll();
    }

    @ApiOperation(value = "Показать книгу")
    @GetMapping(value = "/reactive/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Book> get(@PathVariable("id") String id) {
        return bookReactiveRepository.findById(id);
    }

    @ApiOperation(value = "Добавить книгу")
    @PostMapping(value = "/reactive/book", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> add(@RequestBody Book book) {
        book.setId(null);
        return bookReactiveRepository.save(book).then();
    }

    @ApiOperation(value = "Удалить книгу")
    @DeleteMapping(value = "/reactive/book/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable("id") String id) {
        return bookReactiveRepository.deleteByIdWithComments(id);

    }

    @ApiOperation(value = "Изменить книгу")
    @PutMapping(value = "/reactive/book/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> update(@RequestBody Book book, @PathVariable("id") String id) {
        book.setId(id);
        return bookReactiveRepository.save(book).then();
    }

    @ExceptionHandler({IOException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<String> handleBookNotFound() {
        return ResponseEntity.badRequest().body("Книга не найдена");
    }

}
