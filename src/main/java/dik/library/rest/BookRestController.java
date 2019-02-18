package dik.library.rest;

import dik.library.model.Book;
import dik.library.service.BookService;
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
//@RequestMapping(BookRestController.REST_URL)
@Api(value="REST API для работы с книгами", description = "REST API для работы с книгами")
public class BookRestController {
    //static final String REST_URL = "/rest/book";

    @Autowired
    BookService bookService;

    @ApiOperation(value = "Показать все книги")
    @GetMapping(value = "/rest/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getAll() {
        return bookService.getAllBook();
    }

    @ApiOperation(value = "Показать книгу")
    @GetMapping(value = "/rest/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book get(@PathVariable("id") String id) {
        return bookService.getById(id);
    }

    @ApiOperation(value = "Добавить книгу")
    @PostMapping(value = "/rest/book", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody Book book) {
        book.setId(null);
        bookService.insert(book);
    }

    @ApiOperation(value = "Удалить книгу")
    @DeleteMapping(value = "/rest/book/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        bookService.deleteById(id);
    }

    @ApiOperation(value = "Изменить книгу")
    @PutMapping(value = "/rest/book/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Book book, @PathVariable("id") String id) {
        book.setId(id);
        bookService.update(book);
    }

    @ExceptionHandler({IOException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<String> handleBookNotFound() {
        return ResponseEntity.badRequest().body("Книга не найдена");
    }

}
