package dik.library.testgenre;

import dik.library.dao.genre.GenreDaoJdbcImpl;
import dik.library.dao.genre.GenreRowMapper;
import dik.library.model.Genre;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@JdbcTest
@Import({GenreDaoJdbcImpl.class, GenreRowMapper.class})
public class GenreSmartTest {

    @Autowired
    GenreDaoJdbcImpl genreDaoJdbc;

    @Test
    public void count() {
        Assert.assertEquals(2, genreDaoJdbc.count());
    }

    @Test
    public void getByID() {
        Assert.assertEquals("genrename22", genreDaoJdbc.getById(22).getGenreName());
    }

    @Test
    public void getAll() {
        List<Genre> genres = genreDaoJdbc.getAllGenre();
        Assert.assertEquals("genrename22", genres.get(0).getGenreName());
    }

    @Test
    public void insert() {
        Genre genre = new Genre(24, "genrename24");
        genreDaoJdbc.insert(genre);
        Assert.assertEquals(3, genreDaoJdbc.count());
        Assert.assertEquals(24, genre.getId());
    }

    @Test(expected = Exception.class)
    public void deleteByIdWithException() {
        genreDaoJdbc.deleteById(22);

    }

    @Test
    public void deleteById() {
        genreDaoJdbc.deleteById(23);
        Assert.assertEquals(1, genreDaoJdbc.count());
    }
}
