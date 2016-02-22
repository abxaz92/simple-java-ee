package local.diplom.service.controller;

import local.diplom.service.model.Product;

import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created  by david on 21.02.16
 */

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class Test {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    @GET
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Product test() {
        return em.find(Product.class, "1");
    }

    @POST
    public Product post() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Product product = new Product();
        product.setId("1");
        product.setName("aaaaa");
        utx.begin();
        em.persist(product);
        utx.commit();
        return product;
    }
}
