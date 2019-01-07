package dik.library.testbook;

import dik.library.dao.author.AuthorDaoJdbcImpl;
import dik.library.dao.author.AuthorRowMapper;
import dik.library.dao.book.BookDaoJdbcImpl;
import dik.library.dao.book.BookRowMapper;
import dik.library.dao.genre.GenreDaoJdbcImpl;
import dik.library.dao.genre.GenreRowMapper;
import dik.library.model.Author;
import dik.library.model.Book;
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
@Import({BookDaoJdbcImpl.class, BookRowMapper.class, AuthorDaoJdbcImpl.class, AuthorRowMapper.class, GenreDaoJdbcImpl.class, GenreRowMapper.class})
public class BookSmartTest {

    @Autowired
    BookDaoJdbcImpl bookDaoJdbc;

    @Autowired
    AuthorDaoJdbcImpl authorDaoJdbc;

    @Autowired
    GenreDaoJdbcImpl genreDaoJdbc;

    @Test
    public void count() {
        Assert.assertEquals(2, bookDaoJdbc.count());
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

    @Test
    public void insert() {
        Author author = authorDaoJdbc.getById(101);
        Genre genre = genreDaoJdbc.getById(22);
        Book book = new Book(1005, "name1005", "description1005", author, genre);
        bookDaoJdbc.insert(book);
        Assert.assertEquals(3, bookDaoJdbc.count());
    }

    @Test
    public void deleteById() {
        bookDaoJdbc.deleteById(1004);
        Assert.assertEquals(1, bookDaoJdbc.count());
    }
}
