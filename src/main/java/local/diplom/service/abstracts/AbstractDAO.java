package local.diplom.service.abstracts;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.List;

/**
 * Creator david on 22.02.16 .
 */
public class AbstractDAO<T extends EntityInterface> {
    private Class type;

    @PersistenceContext
    protected EntityManager em;

    @Resource
    private UserTransaction utx;

    public AbstractDAO(Class type) {
        this.type = type;
    }

    public T findById(Long id) {
        return (T) em.find(type, id);
    }

    public void insert(T entity) throws Exception {
        utx.begin();
        em.persist(entity);
        utx.commit();
    }

    public void insert(List<T> entities) throws Exception {
        utx.begin();
        em.persist(entities);
        utx.commit();
    }

    public void deleteById(Long id) throws Exception {
        utx.begin();
        em.remove(findById(id));
        utx.commit();
    }

}
