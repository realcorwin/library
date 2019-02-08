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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    AuthorService authorService;

    @Autowired
    GenreService genreService;

    @Autowired
    BookService bookService;

    @RequestMapping("/books")
    public String books(Model model) {
        List<Book> books = bookService.getAllBook();
        model.addAttribute("books", books);
        return "books";
    }

    @RequestMapping("/book")
    public String book(@RequestParam String id, Model model){
        Book book = bookService.getById(id);
        //addAttributes(model, book);
        List<Genre> genres = genreService.getAllGenre();
        List<Author> authors = authorService.getAllAuthor();
        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "book";
    }

    @RequestMapping(value = "/createBook", method = RequestMethod.GET)
    public String createBook(Model model){
        Book book = new Book();
        //addAttributes(model, book);
        List<Genre> genres = genreService.getAllGenre();
        List<Author> authors = authorService.getAllAuthor();
        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "createBook";
    }

    @RequestMapping(value = "/editBook", method = RequestMethod.POST)
    public String editBook(@ModelAttribute(value = "book") Book book){
        //Author author = book.getAuthor();
        //authorService.update(author);
        //Genre genre = book.getGenre();
        //genreService.update(genre);
        //book = bookService.update(book);
        bookService.update(book);
        //String id = book.getId();
        //return "redirect:/book?id=" + book.getId();
        return "redirect:/books";
    }

    @RequestMapping(value = "/saveBook", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute Book book){
        book = bookService.update(book);
        return "redirect:/book?id=" + book.getId();
    }

    @RequestMapping("/deleteBook")
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
