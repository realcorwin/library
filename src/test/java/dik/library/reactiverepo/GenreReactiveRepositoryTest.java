package dik.library.reactiverepo;

import dik.library.model.Genre;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static dik.library.TestConstants.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class GenreReactiveRepositoryTest {

    @Autowired
    GenreReactiveRepository genreReactiveRepository;

    private Genre genre;

    @Before
    public void init(){
        genre = genreReactiveRepository.save(new Genre(GENRE)).block();
    }

    @Test
    public void testNames() {
        Assert.assertEquals(GENRE, genre.getGenreName());
    }

    @Test
    public void testFindById() {
        final Genre genreFromDb = genreReactiveRepository.findById(genre.getId()).block();
        Assert.assertEquals(genre, genreFromDb);

        Mono<Genre> genreMono = genreReactiveRepository.findById(genre.getId());
        StepVerifier.create(genreMono).expectNext(genre).verifyComplete();
    }

    @Test
    public void testCount(){
        System.out.println(genreReactiveRepository.findAll().blockFirst());
        Assert.assertTrue(genreReactiveRepository.count().block().intValue() > 0);
    }

    @Test
    public void testDeleteAll(){
        genreReactiveRepository.deleteAll().block();
        Assert.assertEquals(0, genreReactiveRepository.count().block().intValue());
    }
}
