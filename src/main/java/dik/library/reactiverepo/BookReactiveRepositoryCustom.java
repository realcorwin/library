package dik.library.reactiverepo;

import reactor.core.publisher.Mono;

public interface BookReactiveRepositoryCustom {

    Mono<Void> deleteByIdWithComments(String id);
}
