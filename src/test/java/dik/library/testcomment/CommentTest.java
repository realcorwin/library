package dik.library.testcomment;

import dik.library.model.Book;
import dik.library.model.Comment;
import dik.library.repository.BookRepository;
import dik.library.repository.CommentRepository;
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
public class CommentTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void count() {
        Assert.assertEquals(3, commentRepository.count());
    }

    @Test
    public void getByID() {
        Assert.assertEquals("Cool", Objects.requireNonNull(commentRepository.findById(501L).orElse(null)).getComment());
    }

    @Test
    public void getAll() {
        List<Comment> comments = commentRepository.findAll();
        Assert.assertEquals("Cool", comments.get(0).getComment());
    }

    @Test
    public void insert() {
        Book book = bookRepository.findById(1003L).orElse(null);
        commentRepository.save(new Comment("Brilliant", book));
        Assert.assertEquals(4, commentRepository.count());
    }

    @Test
    public void deleteById() {
        commentRepository.deleteById(503L);
        Assert.assertEquals(2, commentRepository.count());
    }
}
