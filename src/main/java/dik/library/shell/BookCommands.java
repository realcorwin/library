package dik.library.shell;

import dik.library.model.Book;
import dik.library.service.AuthorService;
import dik.library.service.BookService;
import dik.library.service.GenreService;
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
    public int bookCount(){
        return  bookService.count();
    }
    @ShellMethod("Book get by id")
    public String bookGetById(@ShellOption long id){
        return bookService.getById(id).toString();
    }
    @ShellMethod("Book delete by id")
    public void bookDeleteById(@ShellOption long id){
        bookService.deleteById(id);
    }
    @ShellMethod("Book full insert")
    public void bookFullInsert(@ShellOption long id, @ShellOption String name, @ShellOption String description,
                               @ShellOption long authorId, @ShellOption String firstName, @ShellOption String secondName,
                               @ShellOption long genreId, @ShellOption String genreName){
        bookService.insert(id, name, description, authorId, firstName, secondName, genreId, genreName);
    }
    @ShellMethod("Book insert")
    public void bookInsert(@ShellOption long id, @ShellOption String name, @ShellOption String description,
                           @ShellOption long authorId,
                           @ShellOption long genreId){
        bookService.insert(new Book(id, name, description, authorService.getById(authorId), genreService.getById(genreId)));

    }
    @ShellMethod("Book list")
    public String bookList(){
        return  bookService.getAllBook().toString();
    }

}
