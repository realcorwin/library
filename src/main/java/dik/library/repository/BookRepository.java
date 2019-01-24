package dik.library.repository;

import dik.library.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

    public Book findByAuthorId(String authorId);

    public Book findByGenreId(String genreId);
}
