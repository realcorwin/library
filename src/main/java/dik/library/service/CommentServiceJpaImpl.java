package dik.library.service;

import dik.library.dao.book.BookDaoJpaImpl;
import dik.library.dao.comment.CommentDaoJpaImpl;
import dik.library.model.Book;
import dik.library.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceJpaImpl implements CommentServiceJpa {

    final private CommentDaoJpaImpl commentDaoJpa;

    final private BookDaoJpaImpl bookDaoJpa;

    @Autowired
    public CommentServiceJpaImpl(CommentDaoJpaImpl commentDaoJpa, BookDaoJpaImpl bookDaoJpa) {
        this.commentDaoJpa = commentDaoJpa;
        this.bookDaoJpa = bookDaoJpa;
    }

    @Override
    public int count() {
        return commentDaoJpa.count();
    }

    @Override
    public Comment getById(long id) {
        return commentDaoJpa.getById(id);
    }

    @Override
    public List<Comment> getAllComment() {
        return commentDaoJpa.getAll();
    }

    @Override
    public void insert(long idBook, String comment) {
        Book book = bookDaoJpa.getById(idBook);
        commentDaoJpa.insert(new Comment(comment, book));
    }

    @Override
    public void deleteById(long id) {
        commentDaoJpa.deleteById(id);
    }

    @Override
    public List<Comment> getAllCommentByBookId(long bookId) {
        return commentDaoJpa.getAllByBookId(bookId);
    }
}
