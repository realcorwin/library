package dik.library.reactiverepo;

import dik.library.model.Author;
import dik.library.model.Book;
import dik.library.model.Genre;
import dik.library.repository.AuthorRepository;
import dik.library.repository.BookRepository;
import dik.library.repository.GenreRepository;
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
public class BookReactiveRepositoryTest {


    @Autowired
    BookReactiveRepository bookReactiveRepository;

    @Autowired
    AuthorReactiveRepository authorReactiveRepository;

    @Autowired
    GenreReactiveRepository genreReactiveRepository;


    private Author author;
    private Book book;
    private Genre genre;

    @Before
    public void init(){
        author = authorReactiveRepository.save(new Author(FIRST_NAME, SECOND_NAME)).block();
        genre = genreReactiveRepository.save(new Genre(GENRE)).block();
        book = bookReactiveRepository.save(new Book(BOOK_NAME, BOOK_DESCRIPTION, author, genre)).block();
    }

    @Test
    public void testBookValues(){
        Assert.assertEquals(BOOK_NAME, book.getName());
        Assert.assertEquals(BOOK_DESCRIPTION, book.getDescription());
    }

    @Test
    public void testFindById() {
        final Book bookFromDb = bookReactiveRepository.findById(book.getId()).block();
        Assert.assertEquals(book, bookFromDb);
    }

    @Test
    public void testAuthor(){
        Assert.assertNotNull(book.getAuthor());
    }

    @Test
    public void testGenre(){
        Assert.assertNotNull(book.getGenre());
    }

    @Test
    public void testCount(){
        Assert.assertTrue(bookReactiveRepository.count().block().intValue() > 0);
    }

    @Test
    public void testDeleteAll(){
        bookReactiveRepository.deleteAll().block();
        Assert.assertEquals(0, bookReactiveRepository.count().block().intValue());
    }
}
