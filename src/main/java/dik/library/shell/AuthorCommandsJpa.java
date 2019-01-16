package dik.library.shell;

import dik.library.service.AuthorServiceJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AuthorCommandsJpa {

    private final AuthorServiceJpa authorServiceJpa;

    @Autowired
    public AuthorCommandsJpa(AuthorServiceJpa authorServiceJpa) {
        this.authorServiceJpa = authorServiceJpa;
    }

    @ShellMethod("Author count")
    public long authorCount(){
        return  authorServiceJpa.count();
    }

    @ShellMethod("Author get by id")
    public String authorGetById(@ShellOption long id){
        return authorServiceJpa.getById(id).toString();
    }

    @ShellMethod("Author delete by id")
    public void authorDeleteById(@ShellOption long id){
        authorServiceJpa.deleteById(id);
    }

    @ShellMethod("Author insert")
    public void authorInsert(@ShellOption String firstName, @ShellOption String secondName){
        authorServiceJpa.insert(firstName, secondName);
    }

    @ShellMethod("Author list")
    public String authorList(){
        return  authorServiceJpa.getAllAuthor().toString();
    }
}
