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
    public long genreCount(){
        return  genreService.count();
    }

    @ShellMethod("Genre get by id")
    public String genreGetById(@ShellOption String id){
        return genreService.getById(id).toString();
    }

    @ShellMethod("Genre delete by id")
    public void genreDeleteById(@ShellOption String id){
        genreService.deleteById(id);
    }

    @ShellMethod("Genre insert")
    public void genreInsert(@ShellOption String genreName){
        genreService.insert(genreName);
    }

    @ShellMethod("Genre list")
    public String genreList(){
        return  genreService.getAllGenre().toString();
    }
}
