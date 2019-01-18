package dik.library.testdb;

import dik.library.dao.author.AuthorDaoJpaImpl;
import dik.library.model.Author;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DbTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void replacesDefinedDataSourceWithEmbeddedDefault() throws Exception {
        String product = this.dataSource.getConnection().getMetaData()
                .getDatabaseProductName();
        assertThat(product).isEqualTo("H2");
    }
}
