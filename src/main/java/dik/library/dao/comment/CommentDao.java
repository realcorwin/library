package dik.library.dao.comment;

import dik.library.model.Comment;

import java.util.List;

public interface CommentDao {

    public long count();

    public Comment getById(long id);

    public List<Comment> getAll();

    public void deleteById(long id);

    public void insert(Comment comment);

    public List<Comment> getAllByBookId(long bookId);
}
