package dik.library.testauthor;

import dik.library.model.Author;
import dik.library.repository.AuthorRepository;
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
public class AuthorTest {

    @Autowired
    AuthorRepository authorRepository;

    private Author author;

    @Before
    public void init(){
        author = authorRepository.save(new Author(FIRST_NAME, SECOND_NAME));
    }

    @Test
    public void testAuthorNames(){
        Assert.assertEquals(FIRST_NAME, author.getFirstName());
        Assert.assertEquals(SECOND_NAME, author.getSecondName());
    }

    @Test
    public void testCount(){
        System.out.println(authorRepository.findAll());
        Assert.assertTrue(authorRepository.count() > 0);
    }

    @Test
    public void testDeleteAll(){
        authorRepository.deleteAll();
        Assert.assertEquals(0, authorRepository.count());
    }
}
