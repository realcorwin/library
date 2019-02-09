package dik.library.controller;

import dik.library.model.Author;
import dik.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthorController
{

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author")
    public String author(@RequestParam String id, Model model){
        Author author = authorService.getById(id);
        model.addAttribute("author", author);
        return "author";
    }
    @GetMapping("/authors")
    public String authors(Model model){
        List<Author> authors = authorService.getAllAuthor();
        Author author = new Author();
        model.addAttribute("authors", authors);
        model.addAttribute("author", author);
        return "authors";
    }

    @PostMapping(value = "/deleteAuthor")
    public String deleteAuthor(@ModelAttribute Author author){
        authorService.deleteById(author.getId());
        return "redirect:/authors";
    }

    @PostMapping(value = "/createAuthor")
    public String addAuthor(@ModelAttribute Author author){
        authorService.insert(author.getFirstName(), author.getSecondName());
        return "redirect:/authors";
    }

    @PostMapping(value = "/editAuthor")
    public String editAuthor(@ModelAttribute Author author){
        authorService.update(author);
        return "redirect:/authors";
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleNoGenreDeleteWithBooks(RuntimeException ex) {
        return ResponseEntity.badRequest().body("Удалите сначала книги");
    }
}
