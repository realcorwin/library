package dik.library.reactiverepo;

import dik.library.model.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BookReactiveRepository extends ReactiveMongoRepository<Book, String>, BookReactiveRepositoryCustom {

    public Book findFirstByAuthorId(String authorId);

    public Book findFirstByGenreId(String genreId);
}
