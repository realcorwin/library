package dik.library.reactiverepo;

import dik.library.model.Genre;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GenreReactiveRepository extends ReactiveMongoRepository<Genre, String> {
}
