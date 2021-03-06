package dik.library.repository;

import dik.library.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

    public Book findFirstByAuthorId(String authorId);

    public Book findFirstByGenreId(String genreId);
}
