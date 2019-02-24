package dik.library.reactiverepo;

import dik.library.model.Author;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AuthorReactiveRepository extends ReactiveMongoRepository<Author, String> {
}
