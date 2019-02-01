package dik.library.shell;

import dik.library.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class CommentCommands {

    private final CommentService commentService;

    @Autowired
    public CommentCommands(CommentService commentService) {
        this.commentService = commentService;
    }

    @ShellMethod("Comment count")
    public long commentCount(){
        return  commentService.count();
    }

    @ShellMethod("Comment get by id")
    public String commentGetById(@ShellOption String id){
        return commentService.getById(id).toString();
    }

    @ShellMethod("Comment delete by id")
    public void commentDeleteById(@ShellOption String id){
        commentService.deleteById(id);
    }

    @ShellMethod("Comment insert")
    public void commentInsert(@ShellOption String idBook, @ShellOption String comment){
        commentService.insert(idBook, comment);
    }

    @ShellMethod("Comment list")
    public String commentList(){
        return  commentService.getAllComment().toString();
    }

    @ShellMethod("Comment list by book id")
    public String commentListByBookId(@ShellOption String idBook) {
        return commentService.getAllCommentByBookId(idBook).toString();
    }
}
