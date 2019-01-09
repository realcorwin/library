package dik.library.testauthor;

import dik.library.dao.author.AuthorDaoJdbcImpl;
import dik.library.dao.author.AuthorRowMapper;
import dik.library.model.Author;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@JdbcTest
// @Sql({"/schema-test.sql", "/data-test.sql"}) // так работает
@Import({AuthorDaoJdbcImpl.class, AuthorRowMapper.class})
public class AuthorSmartTest {

    @Autowired
    AuthorDaoJdbcImpl authorDaoJdbc;

    @Test
    public void count() {
        Assert.assertEquals(2, authorDaoJdbc.count());
    }

    @Test
    public void getByID() {
        Assert.assertEquals("firstname101", authorDaoJdbc.getById(101).getFirstName());
    }

    @Test
    public void getAll() {
        List<Author> authors = authorDaoJdbc.getAllAuthor();
        Assert.assertEquals("firstname99", authors.get(0).getFirstName());
    }

    @Test
    public void insert() {
        Author author = new Author(102, "firstname102", "secondname102");
        authorDaoJdbc.insert(author);
        Assert.assertEquals(3, authorDaoJdbc.count());
        Assert.assertEquals(102, author.getId());
    }

    @Test(expected = Exception.class)
    public void deleteByIdWithException() {
        authorDaoJdbc.deleteById(101);

    }

    @Test
    public void deleteById() {
        authorDaoJdbc.deleteById(99);
        Assert.assertEquals(1, authorDaoJdbc.count());
    }
}
