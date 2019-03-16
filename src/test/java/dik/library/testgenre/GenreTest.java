package dik.library.testgenre;

import dik.library.entity.Genre;
import dik.library.repository.GenreRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class GenreTest {

    @Autowired
    GenreRepository genreRepository;

    @Test
    public void count() {
        Assert.assertEquals(2, genreRepository.count());
    }

    @Test
    public void getByID() {
        Assert.assertEquals("genrename22", Objects.requireNonNull(genreRepository.findById(22L).orElse(null)).getGenreName());
    }

    @Test
    public void getAll() {
        List<Genre> genres = genreRepository.findAll();
        Assert.assertEquals("genrename22", genres.get(0).getGenreName());
    }

    @Test
    public void insert() {
        Genre genre = new Genre(24, "genrename24");
        genreRepository.save(genre);
        Assert.assertEquals(3, genreRepository.count());
        Assert.assertEquals(24, genre.getId());
    }

    @Test
    public void deleteById() {
        genreRepository.deleteById(23L);
        Assert.assertEquals(1, genreRepository.count());
    }
}
