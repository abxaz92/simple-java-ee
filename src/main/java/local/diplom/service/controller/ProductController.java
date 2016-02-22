package local.diplom.service.controller;

import local.diplom.service.dao.ProductDAO;
import local.diplom.service.model.Product;

import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created  by david on 21.02.16
 */

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductController {

    @Inject
    private ProductDAO productDAO;

    @GET
    @Path("/{id}")
    public Product test(@PathParam("id") String id) {
        return productDAO.findById(id);
    }

    @POST
    public void post(Product product) {
        productDAO.insert(product);
    }
}
