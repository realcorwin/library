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
        Assert.assertEquals(1, authorDaoJdbc.count());
    }

    @Test
    public void getByID() {
        Assert.assertEquals("firstname101", authorDaoJdbc.getById(101).getFirstName());
    }

    @Test
    public void getAll() {
        List<Author> authors = authorDaoJdbc.getAllAuthor();
        Assert.assertEquals("firstname101", authors.get(0).getFirstName());
    }
}
