package dik.library.batch;

import dik.library.entity.Genre;
import dik.library.repository.GenreRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmbeddedH2GenreItemWriter implements ItemWriter<Genre> {

    @Autowired
    GenreRepository genreRepositoryH2;
    
    @Override
    public void write(List<? extends Genre> genres) throws Exception {
        genreRepositoryH2.saveAll(genres);
    }
}
