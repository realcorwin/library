package dik.library.reactiverepo;

import dik.library.model.Author;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface AuthorReactiveRepository extends ReactiveMongoRepository<Author, String>, AuthorReactiveRepositoryCustom {
    Mono<Void> deleteById(String id);
}
