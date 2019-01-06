package dik.library.testauthor;

import dik.library.dao.author.AuthorDaoJdbcImpl;
import dik.library.dao.author.AuthorRowMapper;
import dik.library.model.Author;
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
@Import(AuthorDaoJdbcImpl.class)
public class AuthorTest {

    @MockBean
    AuthorRowMapper authorRowMapper;

    @MockBean
    NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    AuthorDaoJdbcImpl authorDaoJdbc;

    @Test
    public void testAuthorCount() {
        final Map<String, Object> params = new HashMap<>(1);
        String query = "select count(*) from author";
        when(namedJdbcTemplate.queryForObject(query, params, Integer.class)).thenReturn(1);
        Assert.assertEquals(authorDaoJdbc.count(), 1);
    }

    @Test
    public void testGetById(){
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", 1L);
        String query = "select * from author where id = :id";
        when(namedJdbcTemplate.queryForObject(query, params, authorRowMapper))
                .thenReturn(new Author(1, "name1", "name2"));
        Assert.assertNotNull(authorDaoJdbc.getById(1));
        Assert.assertEquals(authorDaoJdbc.getById(1).getId(), 1);
        Assert.assertEquals(authorDaoJdbc.getById(1).getFirstName(), "name1");
        Assert.assertEquals(authorDaoJdbc.getById(1).getSecondName(), "name2");
    }

    @Test
    public void testGetAllAuthor() {
        String query = "select * from author";
        when(namedJdbcTemplate.query(query, authorRowMapper)).thenReturn(new ArrayList<Author>());
        Assert.assertEquals(authorDaoJdbc.getAllAuthor(), new ArrayList<Author>());
    }
}
