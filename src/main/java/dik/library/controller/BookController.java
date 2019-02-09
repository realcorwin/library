package dik.library.controller;

import dik.library.model.Author;
import dik.library.model.Book;
import dik.library.model.Genre;
import dik.library.service.AuthorService;
import dik.library.service.BookService;
import dik.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookService bookService;

    @Autowired
    public BookController(AuthorService authorService, GenreService genreService, BookService bookService) {
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String books(Model model) {
        List<Book> books = bookService.getAllBook();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/book")
    public String book(@RequestParam String id, Model model){
        Book book = bookService.getById(id);
        addAttributes(model, book);
        return "book";
    }

    @GetMapping(value = "/createBook")
    public String createBook(Model model){
        Book book = new Book();
        addAttributes(model, book);
        return "createBook";
    }

    @PostMapping(value = "/editBook")
    public String editBook(@ModelAttribute(value = "book") Book book){
        bookService.update(book);
        return "redirect:/books";
    }

    @PostMapping(value = "/saveBook")
    public String saveBook(@ModelAttribute Book book){
        book = bookService.update(book);
        return "redirect:/book?id=" + book.getId();
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam String id){
        bookService.deleteById(id);
        return "redirect:/books";
    }

    private void addAttributes(Model model, Book book) {
        List<Genre> genres = genreService.getAllGenre();
        List<Author> authors = authorService.getAllAuthor();
        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
    }

}
