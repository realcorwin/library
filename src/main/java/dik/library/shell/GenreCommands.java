package dik.library.shell;

import dik.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class GenreCommands {

    private final GenreService genreService;

    @Autowired
    public GenreCommands(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod("Genre count")
    public int genreCount(){
        return  genreService.count();
    }

    @ShellMethod("Genre get by id")
    public String genreGetById(@ShellOption long id){
        return genreService.getById(id).toString();
    }

    @ShellMethod("Genre delete by id")
    public void genreDeleteById(@ShellOption long id){
        genreService.deleteById(id);
    }

    @ShellMethod("Genre insert")
    public void genreInsert(@ShellOption long id, @ShellOption String genreName){
        genreService.insert(id, genreName);
    }

    @ShellMethod("Genre list")
    public String genreList(){
        return  genreService.getAllGenre().toString();
    }
}
