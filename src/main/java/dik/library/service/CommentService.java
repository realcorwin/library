package dik.library.service;

import dik.library.model.Comment;

import java.util.List;

public interface CommentService {
    long count();
    Comment getById(String id);
    List<Comment> getAllComment();
    void insert(String idBook, String comment);
    void deleteById(String id);
    public List<Comment> getAllCommentByBookId(String bookId);
}
