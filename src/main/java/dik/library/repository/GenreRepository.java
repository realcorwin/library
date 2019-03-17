package dik.library.repository;

import dik.library.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findFirstByGenreName(String genreName);
}
