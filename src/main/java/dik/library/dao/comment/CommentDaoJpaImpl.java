package dik.library.dao.comment;

import dik.library.model.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class CommentDaoJpaImpl implements CommentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int count() {
        return (int) entityManager.createQuery("select count(c) from Comment c").getSingleResult();
    }

    @Override
    public Comment getById(long id) {
        return entityManager.find(Comment.class, id);
    }

    @Override
    public List<Comment> getAll() {
        return entityManager.createQuery("select c from Comment c", Comment.class).getResultList();
    }

    @Override
    public void deleteById(long id) {
        entityManager.remove(entityManager.find(Comment.class, id));
    }

    @Override
    public void insert(Comment comment) {
        entityManager.persist(comment);
    }
}
