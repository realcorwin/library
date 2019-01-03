package dik.library.dao.book;

import dik.library.dao.author.AuthorDao;
import dik.library.dao.author.AuthorDaoJdbcImpl;
import dik.library.dao.genre.GenreDaoJdbcImpl;
import dik.library.model.Author;
import dik.library.model.Book;
import dik.library.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookRowMapper implements RowMapper<Book> {

    private final AuthorDaoJdbcImpl authorDaoJdbc;

    private final GenreDaoJdbcImpl genreDaoJdbc;

    @Autowired
    public BookRowMapper(AuthorDaoJdbcImpl authorDaoJdbc, GenreDaoJdbcImpl genreDaoJdbc) {
        this.authorDaoJdbc = authorDaoJdbc;
        this.genreDaoJdbc = genreDaoJdbc;
    }

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Author author = authorDaoJdbc.getById(resultSet.getInt("id_author"));
        Genre genre = genreDaoJdbc.getById(resultSet.getInt("id_genre"));
        return new Book(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                author, genre);
    }
}
