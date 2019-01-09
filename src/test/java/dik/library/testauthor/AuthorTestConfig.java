package dik.library.testauthor;

import dik.library.dao.author.AuthorDaoJdbcImpl;
import dik.library.dao.author.AuthorRowMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.mockito.Mockito.mock;

//@TestConfiguration
//@ComponentScan("dik.library.dao.author")
public class AuthorTestConfig {
    /*@Bean
    public AuthorRowMapper authorRowMapper(){
        return mock(AuthorRowMapper.class);
    }

    @Bean
    public AuthorDaoJdbcImpl authorDaoJdbc(){
        //return new AuthorDaoJdbcImpl(mock(NamedParameterJdbcTemplate.class), authorRowMapper());
        return new AuthorDaoJdbcImpl();
    }*/
}
