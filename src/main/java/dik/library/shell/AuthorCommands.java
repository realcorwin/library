package dik.library.shell;

import dik.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AuthorCommands {

    private final AuthorService authorService;

    @Autowired
    public AuthorCommands(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod("Author count")
    public int authorCount(){
        return  authorService.count();
    }

    @ShellMethod("Author get by id")
    public String authorGetById(@ShellOption long id){
        return authorService.getById(id).toString();
    }

    @ShellMethod("Author delete by id")
    public void authorDeleteById(@ShellOption long id){
        authorService.deleteById(id);
    }

    @ShellMethod("Author insert")
    public void authorInsert(@ShellOption long id, @ShellOption String firstName, @ShellOption String secondName){
        authorService.insert(id, firstName, secondName);
    }

    @ShellMethod("Author list")
    public String authorList(){
        return  authorService.getAllAuthor().toString();
    }
}
