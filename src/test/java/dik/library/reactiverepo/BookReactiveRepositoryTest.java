package dik.library.reactiverepo;

import dik.library.model.Author;
import dik.library.model.Book;
import dik.library.model.Genre;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

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

        Mono<Book> bookMono = bookReactiveRepository.findById(book.getId());
        StepVerifier.create(bookMono).expectNext(book).verifyComplete();
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
