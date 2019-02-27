package dik.library.reactiverepo;

import dik.library.model.Author;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static dik.library.TestConstants.FIRST_NAME;
import static dik.library.TestConstants.SECOND_NAME;

@RunWith(SpringRunner.class)
@DataMongoTest
public class AuthorReactiveRepositoryTest {

    @Autowired
    AuthorReactiveRepository authorReactiveRepository;

    private Author author;

    @Before
    public void init(){
        author = authorReactiveRepository.save(new Author(FIRST_NAME, SECOND_NAME)).block();
    }

    @Test
    public void testNames(){
        Assert.assertEquals(FIRST_NAME, author.getFirstName());
        Assert.assertEquals(SECOND_NAME, author.getSecondName());
    }

    @Test
    public void testFindById() {
        final Author authorFromDb = authorReactiveRepository.findById(author.getId()).block();
        Assert.assertEquals(author, authorFromDb);

        Mono<Author> authorMono = authorReactiveRepository.findById(author.getId());
        StepVerifier.create(authorMono).expectNext(author).verifyComplete();
    }

    @Test
    public void testCount(){
        System.out.println(authorReactiveRepository.findAll().blockFirst());
        Assert.assertTrue(authorReactiveRepository.count().block().intValue() > 0);
    }

    @Test
    public void testDeleteAll(){
        authorReactiveRepository.deleteAll().block();
        Assert.assertEquals(0, authorReactiveRepository.count().block().intValue());
    }
}
