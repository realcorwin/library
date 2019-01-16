package dik.library.dao.author;

import dik.library.model.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class AuthorDaoJpaImpl implements AuthorDaoJpa {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        return (Long) entityManager.createQuery("select count(a) from Author a").getSingleResult();
    }

    @Override
    public Author getById(long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public List<Author> getAllAuthor() {
        return entityManager.createQuery("select a from Author a", Author.class).getResultList();
    }

    @Override
    public void deleteById(long id) {
        entityManager.remove(entityManager.find(Author.class, id));
    }

    @Override
    public void insert(Author author) {
        entityManager.persist(author);
    }
}
