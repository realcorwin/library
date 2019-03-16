package dik.library.service;

import dik.library.entity.Comment;

import java.util.List;

public interface CommentService {
    long count();
    Comment getById(long id);
    List<Comment> getAllComment();
    void insert(long idBook, String comment);
    void deleteById(long id);
    public List<Comment> getAllCommentByBookId(long bookId);
}
