package dik.library.shell;

import dik.library.service.GenreServiceJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class GenreCommandsJpa {

    private final GenreServiceJpa genreServiceJpa;

    @Autowired
    public GenreCommandsJpa(GenreServiceJpa genreServiceJpa) {
        this.genreServiceJpa = genreServiceJpa;
    }

    @ShellMethod("Genre count")
    public long genreCount(){
        return  genreServiceJpa.count();
    }

    @ShellMethod("Genre get by id")
    public String genreGetById(@ShellOption long id){
        return genreServiceJpa.getById(id).toString();
    }

    @ShellMethod("Genre delete by id")
    public void genreDeleteById(@ShellOption long id){
        genreServiceJpa.deleteById(id);
    }

    @ShellMethod("Genre insert")
    public void genreInsert(@ShellOption String genreName){
        genreServiceJpa.insert(genreName);
    }

    @ShellMethod("Genre list")
    public String genreList(){
        return  genreServiceJpa.getAllGenre().toString();
    }
}
