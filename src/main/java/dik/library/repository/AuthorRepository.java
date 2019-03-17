package dik.library.repository;

import dik.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findFirstByFirstNameAndSecondName(String firstName, String secondName);
}
