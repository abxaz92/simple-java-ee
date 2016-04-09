package local.diplom.service.dao;

import com.fasterxml.jackson.databind.JsonNode;
import local.diplom.service.common.Service;
import local.diplom.service.model.User;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;
import java.io.IOException;
import java.util.List;

/**
 * Created by david on 22.02.16.
 */
@ApplicationScoped
public class UsersService {
    @Resource
    protected UserTransaction utx;
    @PersistenceContext
    protected EntityManager em;

    public User findById(String id) {
        return em.find(User.class, id);
    }

    public void insert(User entity) throws Exception {
        persist(entity);
    }

    public void insert(List<User> entities) throws Exception {
        persist(entities);
    }

    public List<User> findAll(Integer skip, Integer limit) {
        TypedQuery<User> query;
        query = em.createNamedQuery("User.getAll", User.class);
        if (skip != null)
            query.setFirstResult(skip);
        if (limit != null)
            query.setMaxResults(limit);
        return query.getResultList();
    }

    public User update(String id, JsonNode json) throws Exception {
        User old = findById(id);
        JsonNode res = Service.merge(Service.MAPPER.convertValue(old, JsonNode.class), json);
        User entity = null;
        try {
            entity = (User) Service.MAPPER.treeToValue(res, User.class);
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

    public void deleteById(String id) throws Exception {
        try {
            utx.begin();
            em.remove(findById(id));
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
        }

    }

    public void update(User entity) throws Exception {
        try {
            utx.begin();
            em.merge(entity);
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
        }
    }

}
