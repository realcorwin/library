package dik.library.controller;

import dik.library.model.Genre;
import dik.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GenreController {

    @Autowired
    GenreService genreService;

    @RequestMapping("/genre")
    public String genre(@RequestParam String id, Model model){
        Genre genre = genreService.getById(id);
        model.addAttribute("genre", genre);
        return "genre";
    }
    @RequestMapping("/genres")
    public String genres(Model model){
        List<Genre> genres = genreService.getAllGenre();
        Genre genre = new Genre();
        model.addAttribute("genres", genres);
        model.addAttribute("genre", genre);
        return "genres";
    }

    @RequestMapping(value = "/deleteGenre", method = RequestMethod.POST)
    public String deleteGenre(@ModelAttribute Genre genre){
        genreService.deleteById(genre.getId());
        return "redirect:/genres";
    }

    @RequestMapping(value = "/createGenre", method = RequestMethod.POST)
    public String addGenre(@ModelAttribute Genre genre){
        genreService.insert(genre.getGenreName());
        return "redirect:/genres";
    }

    @RequestMapping(value = "/editGenre", method = RequestMethod.POST)
    public String editGenre(@ModelAttribute Genre genre){
        genreService.update(genre);
        return "redirect:/genres";
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleNoGenreDeleteWithBooks(RuntimeException ex) {
        return ResponseEntity.badRequest().body("Удалите сначала книги");
    }
}
