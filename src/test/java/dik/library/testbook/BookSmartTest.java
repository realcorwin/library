package dik.library.testbook;

import dik.library.dao.author.AuthorDaoJdbcImpl;
import dik.library.dao.author.AuthorRowMapper;
import dik.library.dao.book.BookDaoJdbcImpl;
import dik.library.dao.book.BookRowMapper;
import dik.library.dao.genre.GenreDaoJdbcImpl;
import dik.library.dao.genre.GenreRowMapper;
import dik.library.model.Book;
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
@Import({BookDaoJdbcImpl.class, BookRowMapper.class, AuthorDaoJdbcImpl.class, AuthorRowMapper.class, GenreDaoJdbcImpl.class, GenreRowMapper.class})
public class BookSmartTest {

    @Autowired
    BookDaoJdbcImpl bookDaoJdbc;

    @Test
    public void count() {
        Assert.assertEquals(1, bookDaoJdbc.count());
    }

    @Test
    public void getByID() {
        Assert.assertEquals("description1003", bookDaoJdbc.getById(1003).getDescription());
    }

    @Test
    public void getAll() {
        List<Book> books = bookDaoJdbc.getAllBook();
        Assert.assertEquals("name1003", books.get(0).getName());
    }
}
