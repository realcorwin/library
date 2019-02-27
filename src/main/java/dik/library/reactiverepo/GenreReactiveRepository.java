package dik.library.reactiverepo;

import dik.library.model.Genre;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface GenreReactiveRepository extends ReactiveMongoRepository<Genre, String>, GenreReactiveRepositoryCustom {
    Mono<Void> deleteById(String id);
}
