package dik.library.rest;

import dik.library.model.Book;
import dik.library.service.BookService;
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
public class BookRestController {
    //static final String REST_URL = "/rest/book";

    @Autowired
    BookService bookService;

    @GetMapping(value = "/rest/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getAll() {
        return bookService.getAllBook();
    }

    @GetMapping(value = "/rest/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book get(@PathVariable("id") String id) {
        return bookService.getById(id);
    }

    @PostMapping(value = "/rest/book", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody Book book) {
        bookService.insert(book);
    }

    @DeleteMapping(value = "/rest/book/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        bookService.deleteById(id);
    }

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
