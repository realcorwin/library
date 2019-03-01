package dik.library.reactiverepo;

import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public class GenreReactiveRepositoryImpl implements GenreReactiveRepositoryCustom {

    @Autowired
    private BookReactiveRepository bookRepository;

    @Autowired
    private GenreReactiveRepository genreReactiveRepository;

    @Override
    public Mono<Object> deleteByIdOnlyIfNotInBooks(String id)
    {
        return bookRepository.findFirstByGenreId(id)
                .flatMap(t -> Mono.error(new RuntimeException("Удалите сначала книги")))
                .switchIfEmpty(genreReactiveRepository.deleteById(id));
    }
}
