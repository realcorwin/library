package dik.library.repository;

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
public class CommentTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    GenreRepository genreRepository;

    private Comment comment;
    private Author author;
    private Book book;
    private Genre genre;

    @Before
    public void init(){
        author = authorRepository.save(new Author(FIRST_NAME, SECOND_NAME));
        genre = genreRepository.save(new Genre(GENRE));
        book = bookRepository.save(new Book(BOOK_NAME, BOOK_DESCRIPTION, author, genre));
        comment = commentRepository.save(new Comment(COMMENT, book));
    }

    @Test
    public void testComment(){
        Assert.assertEquals(COMMENT, comment.getComment());
    }

    @Test
    public void testCount(){
        System.out.println(commentRepository.findAll());
        Assert.assertTrue(commentRepository.count() > 0);
    }

    @Test
    public void testDeleteAll(){
        commentRepository.deleteAll();
        Assert.assertEquals(0, commentRepository.count());
    }
}
