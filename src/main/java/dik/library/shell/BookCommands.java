package dik.library.shell;

import dik.library.entity.Book;
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
    public String bookGetById(@ShellOption long id){
        return bookService.getById(id).toString();
    }
    @ShellMethod("Book delete by id")
    public void bookDeleteById(@ShellOption long id){
        bookService.deleteById(id);
    }
    @ShellMethod("Book insert")
    public void bookInsert(@ShellOption String name, @ShellOption String description,
                           @ShellOption long authorId,
                           @ShellOption long genreId){
        /*Author author = authorService.getById(authorId);
        Genre genre = genreService.getById(genreId);
        Book book = new Book();
        book.setAuthor(author);
        book.setGenre(genre);
        book.setName(name);
        book.setDescription(description);
        bookService.insert(book);*/
        bookService.insert(new Book(name, description, authorService.getById(authorId), genreService.getById(genreId)));

    }
    @ShellMethod("Book list")
    public String bookList(){
        return  bookService.getAllBook().toString();
    }

}
