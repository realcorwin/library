package dik.library.repository;

import dik.library.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "book")
public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

    public Book findFirstByAuthorId(String authorId);

    public Book findFirstByGenreId(String genreId);
}
