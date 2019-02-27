package dik.library.reactiverepo;

import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public class AuthorReactiveRepositoryImpl implements AuthorReactiveRepositoryCustom {

    @Autowired
    private BookReactiveRepository bookRepository;

    @Autowired
    private AuthorReactiveRepository authorReactiveRepository;

    @Override
    public Mono<Object> deleteByIdOnlyIfNotInBooks(String id)
    {
        return bookRepository.findFirstByAuthorId(id)
                .flatMap(t -> Mono.error(new RuntimeException("Удалите сначала книги")))
                .switchIfEmpty(authorReactiveRepository.deleteById(id));
    }
}
