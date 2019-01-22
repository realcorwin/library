package dik.library.shell;

import dik.library.model.Book;
import dik.library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class BookCommands {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private GenreService genreService;

    @ShellMethod("Book count")
    public long bookCount(){
        return  bookService.count();
    }
    @ShellMethod("Book get by id")
    public String bookGetById(@ShellOption String id){
        return bookService.getById(id).toString();
    }
    @ShellMethod("Book delete by id")
    public void bookDeleteById(@ShellOption String id){
        bookService.deleteById(id);
    }
    @ShellMethod("Book insert")
    public void bookInsert(@ShellOption String name, @ShellOption String description,
                           @ShellOption String authorId,
                           @ShellOption String genreId){
        bookService.insert(new Book(name, description, authorService.getById(authorId), genreService.getById(genreId)));
    }
    @ShellMethod("Book list")
    public String bookList(){
        return  bookService.getAllBook().toString();
    }

}
