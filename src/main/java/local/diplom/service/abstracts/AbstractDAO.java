package local.diplom.service.abstracts;

import com.fasterxml.jackson.databind.JsonNode;
import local.diplom.service.common.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.io.IOException;
import java.util.List;

/**
 * Creator david on 22.02.16 .
 */
public class AbstractDAO<T extends EntityInterface> {
    @PersistenceContext
    protected EntityManager em;
    private Class type;
    @Resource
    private UserTransaction utx;

    public AbstractDAO(Class type) {
        this.type = type;
    }

    public T findById(Long id) {
        return (T) em.find(type, id);
    }

    public void insert(T entity) throws Exception {
        persist(entity);
    }

    public void insert(List<T> entities) throws Exception {
        persist(entities);
    }

    public void deleteById(Long id) throws Exception {
        try {
            utx.begin();
            em.remove(findById(id));
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
        }

    }

    public void update(T entity) throws Exception {
        try {
            utx.begin();
            em.merge(entity);
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
        }
    }

    public T update(Long id, JsonNode json) throws Exception {
        T old = findById(id);
        JsonNode res = Service.merge(Service.MAPPER.convertValue(old, JsonNode.class), json);
        T entity = null;
        try {
            entity = (T) Service.MAPPER.treeToValue(res, this.type);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        update(entity);
        return entity;
    }

    private void persist(Object object) throws Exception {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
        }
    }

}
