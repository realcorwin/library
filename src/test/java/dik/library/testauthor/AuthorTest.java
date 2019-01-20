package dik.library.testauthor;

import dik.library.model.Author;
import dik.library.repository.AuthorRepository;
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

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AuthorTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void count() {
        Assert.assertEquals(2, authorRepository.count());
    }

    @Test
    public void getByID() {
        Assert.assertEquals("firstname101", Objects.requireNonNull(authorRepository.findById(101L).orElse(null)).getFirstName());
    }

    @Test
    public void getAll() {
        List<Author> authors = authorRepository.findAll();
        Assert.assertEquals("firstname99", authors.get(0).getFirstName());
    }

    @Test
    public void insert() {
        Author author = new Author(102, "firstname102", "secondname102");
        authorRepository.save(author);
        Assert.assertEquals(3, authorRepository.count());
        Assert.assertEquals(102, author.getId());
    }

    @Test
    public void deleteById() {
        authorRepository.deleteById(99L);
        Assert.assertEquals(1, authorRepository.count());
    }
}
