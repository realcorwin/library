package dik.library.dao.book;

import dik.library.model.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class BookDaoJpaImpl implements BookDaoJpa {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        return (Long) entityManager.createQuery("select count(b) from Book b").getSingleResult();
    }

    @Override
    public Book getById(long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> getAllBook() {
        return entityManager.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    public void deleteById(long id) {
        entityManager.remove(entityManager.find(Book.class, id));
    }

    @Override
    public void insert(Book book) {
        entityManager.persist(book);
    }
}
