package dik.library.testgenre;

import dik.library.dao.genre.GenreDaoJpaImpl;
import dik.library.model.Genre;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(GenreDaoJpaImpl.class)
public class GenreJpaTest {

    @Autowired
    GenreDaoJpaImpl genreDaoJpa;

    @Test
    public void count() {
        Assert.assertEquals(2, genreDaoJpa.count());
    }

    @Test
    public void getByID() {
        Assert.assertEquals("genrename22", genreDaoJpa.getById(22).getGenreName());
    }

    @Test
    public void getAll() {
        List<Genre> genres = genreDaoJpa.getAllGenre();
        Assert.assertEquals("genrename22", genres.get(0).getGenreName());
    }

    @Test
    public void insert() {
        Genre genre = new Genre(24, "genrename24");
        genreDaoJpa.insert(genre);
        Assert.assertEquals(3, genreDaoJpa.count());
        Assert.assertEquals(24, genre.getId());
    }

    @Test
    public void deleteById() {
        genreDaoJpa.deleteById(23);
        Assert.assertEquals(1, genreDaoJpa.count());
    }
}
