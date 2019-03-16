package dik.library.testbook;

import dik.library.entity.Author;
import dik.library.entity.Book;
import dik.library.entity.Genre;
import dik.library.repository.AuthorRepository;
import dik.library.repository.BookRepository;
import dik.library.repository.GenreRepository;
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

import java.util.List;
import java.util.Objects;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BookTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    GenreRepository genreRepository;

    @Test
    public void count() {
        Assert.assertEquals(2, bookRepository.count());
    }

    @Test
    public void getByID() {
        Assert.assertEquals("description1003", Objects.requireNonNull(bookRepository.findById(1003L).orElse(null)).getDescription());
    }

    @Test
    public void getAll() {
        List<Book> books = bookRepository.findAll();
        Assert.assertEquals("name1003", books.get(0).getName());
    }

    @Test
    public void insert() {
        Author author = authorRepository.findById(101L).orElse(null);
        Genre genre = genreRepository.findById(22L).orElse(null);
        Book book = new Book(1005, "name1005", "description1005", author, genre);
        bookRepository.save(book);
        Assert.assertEquals(3, bookRepository.count());
    }

    @Test
    public void deleteById() {
        bookRepository.deleteById(1004L);
        Assert.assertEquals(1, bookRepository.count());
    }
}
