package dik.library.dao.author;

import dik.library.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbcImpl implements AuthorDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    private final AuthorRowMapper authorRowMapper;

    @Autowired
    public AuthorDaoJdbcImpl(NamedParameterJdbcTemplate namedJdbcTemplate, AuthorRowMapper authorRowMapper) {
        this.namedJdbcTemplate = namedJdbcTemplate;
        this.authorRowMapper = authorRowMapper;
    }

    @Override
    public int count() {
        Map<String, Object> params = new HashMap<>(1);
        String query = "select count(*) from author";
        return namedJdbcTemplate.queryForObject(query, params, Integer.class);
    }

    @Override
    public Author getById(int id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        String query = "select * from author where id = :id";
        return namedJdbcTemplate.queryForObject(query, params, authorRowMapper);
    }

    @Override
    public List<Author> getAllAuthor() {
        String query = "select * from author";
        return namedJdbcTemplate.query(query, authorRowMapper);
    }

    @Override
    public void deleteById(int id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        String query = "delete from author where id = :id";
        namedJdbcTemplate.update(query, params);
    }

    @Override
    public void insert(Author author) {
        final Map<String, Object> params = new HashMap<>(3);
        params.put("id", author.getId());
        params.put("firstName", author.getFirstName());
        params.put("secondName", author.getSecondName());
        String query = "insert into author (id, firstName, secondName) values (:id, :firstName, :secondName)";
        namedJdbcTemplate.update(query, params);
    }
}
