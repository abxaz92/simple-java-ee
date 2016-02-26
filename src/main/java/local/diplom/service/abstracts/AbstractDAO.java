package local.diplom.service.abstracts;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.List;

/**
 * Created by david on 22.02.16 .
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

    public T findById(String id) {
        return (T) em.find(type, id);
    }

    /*public List<T> findAll() {

    }*/

    public void insert(T entity) {
        try {
            utx.begin();
            em.persist(entity); //em.merge(u); for updates
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
