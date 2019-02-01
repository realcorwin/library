package dik.library.repository;

import dik.library.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    public List<Comment> findAllByBookId(String bookId);
    public void deleteByBookId(String bookId);
}
