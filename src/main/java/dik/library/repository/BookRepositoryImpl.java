package dik.library.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

//@Repository
public class BookRepositoryImpl implements BookRepositoryCustom {

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    @Lazy
    @Autowired
    public BookRepositoryImpl(BookRepository bookRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void deleteByIdWithComments(String id) {
        commentRepository.deleteByBookId(id);
        bookRepository.deleteById(id);

    }
}
