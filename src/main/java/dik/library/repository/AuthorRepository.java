package dik.library.repository;

import dik.library.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "author")
public interface AuthorRepository extends MongoRepository<Author, String> {
}
