package dik.library.reactiverepo;

import dik.library.model.Comment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.List;

public interface CommentReactiveRepository extends ReactiveMongoRepository<Comment, String> {

    public List<Comment> findAllByBookId(String bookId);
    public void deleteByBookId(String bookId);
}
