package dik.library.batch;

import dik.library.entity.Author;
import dik.library.repository.AuthorRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmbeddedH2AuthorItemWriter implements ItemWriter<Author> {

    @Autowired
    AuthorRepository authorRepositoryH2;

    @Override
    public void write(List<? extends Author> authors) throws Exception {
        authorRepositoryH2.saveAll(authors);
    }
}
