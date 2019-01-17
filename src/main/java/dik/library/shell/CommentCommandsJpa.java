package dik.library.shell;

import dik.library.service.CommentServiceJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class CommentCommandsJpa {

    private final CommentServiceJpa commentServiceJpa;

    @Autowired
    public CommentCommandsJpa(CommentServiceJpa commentServiceJpa) {
        this.commentServiceJpa = commentServiceJpa;
    }

    @ShellMethod("Comment count")
    public long commentCount(){
        return  commentServiceJpa.count();
    }

    @ShellMethod("Comment get by id")
    public String commentGetById(@ShellOption long id){
        return commentServiceJpa.getById(id).toString();
    }

    @ShellMethod("Comment delete by id")
    public void commentDeleteById(@ShellOption long id){
        commentServiceJpa.deleteById(id);
    }

    @ShellMethod("Comment insert")
    public void commentInsert(@ShellOption long idBook, @ShellOption String comment){
        commentServiceJpa.insert(idBook, comment);
    }

    @ShellMethod("Comment list")
    public String commentList(){
        return  commentServiceJpa.getAllComment().toString();
    }

    @ShellMethod("Comment list by book id")
    public String commentListByBookId(@ShellOption long idBook) {
        return commentServiceJpa.getAllCommentByBookId(idBook).toString();
    }
}
