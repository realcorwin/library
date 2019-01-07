package dik.library.dao.book;

import dik.library.dao.author.AuthorDaoJdbcImpl;
import dik.library.dao.genre.GenreDaoJdbcImpl;
import dik.library.model.Author;
import dik.library.model.Book;
import dik.library.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbcImpl implements BookDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    private final BookRowMapper bookRowMapper;

    private final AuthorDaoJdbcImpl authorDaoJdbc;

    private final GenreDaoJdbcImpl genreDaoJdbc;

    @Autowired
    public BookDaoJdbcImpl(NamedParameterJdbcTemplate namedJdbcTemplate, BookRowMapper bookRowMapper, AuthorDaoJdbcImpl authorDaoJdbc, GenreDaoJdbcImpl genreDaoJdbc) {
        this.namedJdbcTemplate = namedJdbcTemplate;
        this.bookRowMapper = bookRowMapper;
        this.authorDaoJdbc = authorDaoJdbc;
        this.genreDaoJdbc = genreDaoJdbc;
    }

    @Override
    public int count() {
        String query = "select count(*) from book";
        final Map<String, Object> params = new HashMap<>(1);
        return namedJdbcTemplate.queryForObject(query, params,Integer.class);
    }

    @Override
    public Book getById(long id) {
        String query = "select b.id, b.name, b.description, a.id aid, a.firstname afirstname, a.secondname asecondname, g.id gid, g.genrename ggenrename from book b inner join author a on a.id = b.id_author inner join genre g on g.id = b.id_genre where b.id = :id";
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return namedJdbcTemplate.queryForObject(query, params, bookRowMapper);
    }

    @Override
    public List<Book> getAllBook() {
        String query = "select b.id, b.name, b.description, a.id aid, a.firstname afirstname, a.secondname asecondname, g.id gid, g.genrename ggenrename from book b inner join author a on a.id = b.id_author inner join genre g on g.id = b.id_genre order by asecondname";
                //"select * from book";
        return namedJdbcTemplate.query(query, bookRowMapper);
    }

    @Override
    public void deleteById(long id) {
        String query = "delete from book where id = :id";
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        namedJdbcTemplate.update(query, params);
    }

    @Override
    public void insert(Book book) {
        Author author = book.getAuthor();
        Genre genre = book.getGenre();
        String query = "insert into book (id, name, description, id_author, id_genre) " +
                "values(:id, :name, :description, :id_author, :id_genre)";
        final Map<String, Object> params = new HashMap<>(5);
        params.put("id", book.getId());
        params.put("name", book.getName());
        params.put("description", book.getDescription());
        namedJdbcTemplate.update(query, params);
        authorDaoJdbc.insert(author);
        genreDaoJdbc.insert(genre);
    }
}
