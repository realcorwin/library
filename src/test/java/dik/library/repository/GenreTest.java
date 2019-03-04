package dik.library.repository;

import dik.library.model.Genre;
import dik.library.repository.GenreRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static dik.library.TestConstants.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class GenreTest {

    @Autowired
    GenreRepository genreRepository;

    private Genre genre;

    @Before
    public void init(){
        genre = genreRepository.save(new Genre(GENRE));
    }

    @Test
    public void testGenre(){
        Assert.assertEquals(GENRE, genre.getGenreName());
    }

    @Test
    public void testCount(){
        System.out.println(genreRepository.findAll());
        Assert.assertTrue(genreRepository.count() > 0);
    }

    @Test
    public void testDeleteAll(){
        genreRepository.deleteAll();
        Assert.assertEquals(0, genreRepository.count());
    }
}
