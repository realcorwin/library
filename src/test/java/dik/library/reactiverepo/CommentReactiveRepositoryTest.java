package dik.library.reactiverepo;

import dik.library.model.Author;
import dik.library.model.Book;
import dik.library.model.Comment;
import dik.library.model.Genre;
import dik.library.repository.AuthorRepository;
import dik.library.repository.BookRepository;
import dik.library.repository.CommentRepository;
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
public class CommentReactiveRepositoryTest {

    @Autowired
    BookReactiveRepository bookReactiveRepository;

    @Autowired
    CommentReactiveRepository commentReactiveRepository;

    @Autowired
    AuthorReactiveRepository authorReactiveRepository;

    @Autowired
    GenreReactiveRepository genreReactiveRepository;

    private Comment comment;
    private Author author;
    private Book book;
    private Genre genre;

    @Before
    public void init(){
        author = authorReactiveRepository.save(new Author(FIRST_NAME, SECOND_NAME)).block();
        genre = genreReactiveRepository.save(new Genre(GENRE)).block();
        book = bookReactiveRepository.save(new Book(BOOK_NAME, BOOK_DESCRIPTION, author, genre)).block();
        comment = commentReactiveRepository.save(new Comment(COMMENT, book)).block();
    }

    @Test
    public void testComment(){
        Assert.assertEquals(COMMENT, comment.getComment());
    }

    @Test
    public void testFindById() {
        final Comment commentFromDb = commentReactiveRepository.findById(comment.getId()).block();
        Assert.assertEquals(comment, commentFromDb);
    }

    @Test
    public void testCount(){
        System.out.println(commentReactiveRepository.findAll().blockFirst());
        Assert.assertTrue(commentReactiveRepository.count().block().intValue() > 0);
    }

    @Test
    public void testDeleteAll(){
        commentReactiveRepository.deleteAll().block();
        Assert.assertEquals(0, commentReactiveRepository.count().block().intValue());
    }
}
