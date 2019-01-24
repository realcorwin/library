package dik.library.event;

import dik.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CascadeDeleteMongoEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Object> event) throws RuntimeException {
        if (Objects.equals(event.getCollectionName(), "genre") || Objects.equals(event.getCollectionName(), "author")) {
            String id = null;
            for (Object key : event.getSource().values()) {
                id = String.valueOf(key);
            }
            if (bookRepository.findFirstByGenreId(id) != null || bookRepository.findFirstByAuthorId(id) != null) throw new RuntimeException();
        }
    }
}
