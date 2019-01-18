package dik.library.testbook;

import dik.library.dao.author.AuthorDaoJpaImpl;
import dik.library.dao.book.BookDaoJpaImpl;
import dik.library.dao.genre.GenreDaoJpaImpl;
import dik.library.model.Author;
import dik.library.model.Book;
import dik.library.model.Genre;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import({BookDaoJpaImpl.class, AuthorDaoJpaImpl.class, GenreDaoJpaImpl.class})
public class BookJpaTest {

    @Autowired
    BookDaoJpaImpl bookDaoJpa;

    @Autowired
    AuthorDaoJpaImpl authorDaoJpa;

    @Autowired
    GenreDaoJpaImpl genreDaoJpa;

    @Test
    public void count() {
        Assert.assertEquals(2, bookDaoJpa.count());
    }

    @Test
    public void getByID() {
        Assert.assertEquals("description1003", bookDaoJpa.getById(1003).getDescription());
    }

    @Test
    public void getAll() {
        List<Book> books = bookDaoJpa.getAllBook();
        Assert.assertEquals("name1003", books.get(0).getName());
    }

    @Test
    public void insert() {
        Author author = authorDaoJpa.getById(101);
        Genre genre = genreDaoJpa.getById(22);
        Book book = new Book(1005, "name1005", "description1005", author, genre);
        bookDaoJpa.insert(book);
        Assert.assertEquals(3, bookDaoJpa.count());
    }

    @Test
    public void deleteById() {
        bookDaoJpa.deleteById(1004);
        Assert.assertEquals(1, bookDaoJpa.count());
    }
}
