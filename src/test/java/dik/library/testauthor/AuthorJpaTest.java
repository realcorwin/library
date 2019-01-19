package dik.library.testauthor;

import dik.library.dao.author.AuthorDaoJpaImpl;
import dik.library.model.Author;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
//@Sql("/data-test.sql")
@Import(AuthorDaoJpaImpl.class)
public class AuthorJpaTest {

    @Autowired
    AuthorDaoJpaImpl authorDaoJpa;

    @Test
    public void count() {
        Assert.assertEquals(2, authorDaoJpa.count());
    }

    @Test
    public void getByID() {
        Assert.assertEquals("firstname101", authorDaoJpa.getById(101).getFirstName());
    }

    @Test
    public void getAll() {
        List<Author> authors = authorDaoJpa.getAllAuthor();
        Assert.assertEquals("firstname99", authors.get(0).getFirstName());
    }

    @Test
    public void insert() {
        Author author = new Author(102, "firstname102", "secondname102");
        authorDaoJpa.insert(author);
        Assert.assertEquals(3, authorDaoJpa.count());
        Assert.assertEquals(102, author.getId());
    }

    @Test
    public void deleteById() {
        authorDaoJpa.deleteById(99);
        Assert.assertEquals(1, authorDaoJpa.count());
    }
}
