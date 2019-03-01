package dik.library.reactiverepo;

import reactor.core.publisher.Mono;

public interface GenreReactiveRepositoryCustom {
    Mono<Object> deleteByIdOnlyIfNotInBooks(String id);
}
