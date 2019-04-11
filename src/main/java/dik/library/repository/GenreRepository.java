package dik.library.repository;

import dik.library.model.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "genre")
public interface GenreRepository extends MongoRepository<Genre, String> {
}
