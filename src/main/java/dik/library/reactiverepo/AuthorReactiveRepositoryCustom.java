package dik.library.reactiverepo;

import reactor.core.publisher.Mono;

public interface AuthorReactiveRepositoryCustom {
    Mono<Object> deleteByIdOnlyIfNotInBooks(String id);
}
