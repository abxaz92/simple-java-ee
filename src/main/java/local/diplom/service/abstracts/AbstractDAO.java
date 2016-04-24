package local.diplom.service.abstracts;

import com.fasterxml.jackson.databind.JsonNode;
import local.diplom.service.common.ExceptionFactory;
import local.diplom.service.common.Service;
import local.diplom.service.model.Product;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;
import java.io.IOException;
import java.util.List;

/**
 * Creator david on 22.02.16 .
 */
public class AbstractDAO<T extends EntityInterface> extends ExceptionFactory {
    @PersistenceContext
    protected EntityManager em;
    @Resource
    protected UserTransaction utx;
    private Class type;
    protected String tablename;

    public AbstractDAO(String tablename, Class type) {
        this.type = type;
        this.tablename = tablename;
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

    public Object findAll(Integer skip, Integer limit, String count) {
        if (count == null) {
            TypedQuery<T> query = em.createNamedQuery(tablename + ".getAll", type);
            if (skip != null)
                query.setFirstResult(skip);
            if (limit != null)
                query.setMaxResults(limit);
            return query.getResultList();
        } else {
            String sql = "SELECT COUNT(d) FROM " + tablename + " d";
            Query query = em.createQuery(sql);
            return query.getSingleResult();
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
