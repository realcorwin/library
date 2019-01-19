package dik.library.service;

import dik.library.dao.book.BookDaoJpaImpl;
import dik.library.dao.comment.CommentDaoJpaImpl;
import dik.library.model.Book;
import dik.library.model.Comment;
import dik.library.repository.BookRepository;
import dik.library.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public long count() {
        return commentRepository.count();
    }

    @Override
    public Comment getById(long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public void insert(long idBook, String comment) {
        Book book = bookRepository.getOne(idBook);
        commentRepository.save(new Comment(comment, book));
    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getAllCommentByBookId(long bookId) {
        return commentRepository.findAllByBook(bookId);
    }
}
