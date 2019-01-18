package dik.library.dao.genre;

import dik.library.model.Genre;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class GenreDaoJpaImpl implements GenreDaoJpa {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        return (Long) entityManager.createQuery("select count(g) from Genre g").getSingleResult();
    }

    @Override
    public Genre getById(long id) {
        return entityManager.find(Genre.class, id);
    }

    @Override
    public List<Genre> getAllGenre() {
        return entityManager.createQuery("select g from Genre g", Genre.class).getResultList();
    }

    @Override
    public void deleteById(long id) {
        entityManager.remove(entityManager.find(Genre.class, id));
    }

    @Override
    public void insert(Genre genre) {
        entityManager.merge(genre);
    }
}
