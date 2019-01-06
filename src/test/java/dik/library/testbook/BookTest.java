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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@JdbcTest
@Import({BookDaoJdbcImpl.class, AuthorDaoJdbcImpl.class, GenreDaoJdbcImpl.class, AuthorRowMapper.class, GenreRowMapper.class})
public class BookTest {

    @MockBean
    BookRowMapper bookRowMapper;

    @MockBean
    NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    BookDaoJdbcImpl bookDaoJdbc;

    @Test
    public void testAuthorCount() {
        final Map<String, Object> params = new HashMap<>(1);
        String query = "select count(*) from book";
        when(namedJdbcTemplate.queryForObject(query, params, Integer.class)).thenReturn(1);
        Assert.assertEquals(bookDaoJdbc.count(), 1);
    }

    @Test
    public void testGetById(){
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", 1L);
        String query = "select b.id, b.name, b.description, a.id aid, a.firstname afirstname, a.secondname asecondname, g.id gid, g.genrename ggenrename from book b inner join author a on a.id = b.id_author inner join genre g on g.id = b.id_genre where id = :id";
        when(namedJdbcTemplate.queryForObject(query, params, bookRowMapper))
                .thenReturn(new Book(1, "nameBook", "description",
                        new Author(1, "name1", "name2"),
                        new Genre(1, "genre1")));
        Assert.assertNotNull(bookDaoJdbc.getById(1));
    }

    @Test
    public void testGetAllBook() {
        String query = "select * from book";
        when(namedJdbcTemplate.query(query, bookRowMapper)).thenReturn(new ArrayList<Book>());
        Assert.assertEquals(bookDaoJdbc.getAllBook(), new ArrayList<Book>());
    }

}
