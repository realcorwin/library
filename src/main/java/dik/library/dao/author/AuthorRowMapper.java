package dik.library.dao.author;

import dik.library.model.Author;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuthorRowMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Author(resultSet.getLong("id"),
                resultSet.getString("firstName"),
                resultSet.getString("secondName"));
    }
}
