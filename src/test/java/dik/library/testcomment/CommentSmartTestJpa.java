package dik.library.testcomment;

import dik.library.dao.author.AuthorDaoJpaImpl;
import dik.library.dao.book.BookDaoJpaImpl;
import dik.library.dao.comment.CommentDaoJpaImpl;
import dik.library.dao.genre.GenreDaoJpaImpl;
import dik.library.model.Author;
import dik.library.model.Book;
import dik.library.model.Comment;
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
@Import({BookDaoJpaImpl.class, CommentDaoJpaImpl.class})
public class CommentSmartTestJpa {

    @Autowired
    BookDaoJpaImpl bookDaoJpa;

    @Autowired
    CommentDaoJpaImpl commentDaoJpa;

    @Test
    public void count() {
        Assert.assertEquals(3, commentDaoJpa.count());
    }

    @Test
    public void getByID() {
        Assert.assertEquals("Cool", commentDaoJpa.getById(501).getComment());
    }

    @Test
    public void getAll() {
        List<Comment> comments = commentDaoJpa.getAll();
        Assert.assertEquals("Cool", comments.get(0).getComment());
    }

    @Test
    public void insert() {
        Book book = bookDaoJpa.getById(1003);
        commentDaoJpa.insert(new Comment("Brilliant", book));
        Assert.assertEquals(4, commentDaoJpa.count());
    }

    @Test
    public void deleteById() {
        commentDaoJpa.deleteById(503);
        Assert.assertEquals(2, commentDaoJpa.count());
    }
}
