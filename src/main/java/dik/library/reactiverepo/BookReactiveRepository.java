package dik.library.reactiverepo;

import dik.library.model.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface BookReactiveRepository extends ReactiveMongoRepository<Book, String>, BookReactiveRepositoryCustom {

    Mono<Book> findFirstByAuthorId(String authorId);

    Mono<Book> findFirstByGenreId(String genreId);
}
