package dik.library.testgenre;

import dik.library.dao.genre.GenreDaoJdbcImpl;
import dik.library.dao.genre.GenreRowMapper;
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
@Import(GenreDaoJdbcImpl.class)
public class GenreTest {

    @MockBean
    GenreRowMapper genreRowMapper;

    @MockBean
    NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    GenreDaoJdbcImpl genreDaoJdbc;

    @Test
    public void testCount() {
        final Map<String, Object> params = new HashMap<>(1);
        String query = "select count(*) from genre";
        when(namedJdbcTemplate.queryForObject(query, params, Integer.class)).thenReturn(1);
        Assert.assertEquals(genreDaoJdbc.count(), 1);
    }

    @Test
    public void testGetById(){
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", 1L);
        String query = "select * from genre where id = :id";
        when(namedJdbcTemplate.queryForObject(query, params, genreRowMapper))
                .thenReturn(new Genre(1, "genre1"));
        Assert.assertNotNull(genreDaoJdbc.getById(1));
        Assert.assertEquals(genreDaoJdbc.getById(1).getId(), 1);
        Assert.assertEquals(genreDaoJdbc.getById(1).getGenreName(), "genre1");
    }

    @Test
    public void testGetAllGenre() {
        String query = "select * from genre";
        when(namedJdbcTemplate.query(query, genreRowMapper)).thenReturn(new ArrayList<Genre>());
        Assert.assertEquals(genreDaoJdbc.getAllGenre(), new ArrayList<Genre>());
    }

}
