package dik.library.controller;

import dik.library.model.Author;
import dik.library.model.Genre;
import dik.library.service.AuthorService;
import dik.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthorController
{

    @Autowired
    AuthorService authorService;

    @RequestMapping("/author")
    public String author(@RequestParam String id, Model model){
        Author author = authorService.getById(id);
        model.addAttribute("author", author);
        return "author";
    }
    @RequestMapping("/authors")
    public String authors(Model model){
        List<Author> authors = authorService.getAllAuthor();
        Author author = new Author();
        model.addAttribute("authors", authors);
        model.addAttribute("author", author);
        return "authors";
    }

    @RequestMapping(value = "/deleteAuthor", method = RequestMethod.POST)
    public String deleteAuthor(@ModelAttribute Author author){
        authorService.deleteById(author.getId());
        return "redirect:/authors";
    }

    @RequestMapping(value = "/createAuthor", method = RequestMethod.POST)
    public String addAuthor(@ModelAttribute Author author){
        authorService.insert(author.getFirstName(), author.getSecondName());
        return "redirect:/authors";
    }

    @RequestMapping(value = "/editAuthor", method = RequestMethod.POST)
    public String editAuthor(@ModelAttribute Author author){
        authorService.update(author);
        return "redirect:/authors";
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleNoGenreDeleteWithBooks(RuntimeException ex) {
        return ResponseEntity.badRequest().body("Удалите сначала книги");
    }
}
