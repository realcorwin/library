package dik.library.reactiverepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import reactor.core.publisher.Mono;

//@Repository
public class BookReactiveRepositoryImpl implements BookReactiveRepositoryCustom {

    private final BookReactiveRepository bookRepository;

    private final CommentReactiveRepository commentRepository;

    @Lazy
    @Autowired
    public BookReactiveRepositoryImpl(BookReactiveRepository bookRepository, CommentReactiveRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Mono<Void> deleteByIdWithComments(String id) {
        return commentRepository.deleteByBookId(id).then(bookRepository.deleteById(id));
    }
}
