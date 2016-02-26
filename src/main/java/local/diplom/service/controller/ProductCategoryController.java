package local.diplom.service.controller;

import local.diplom.service.dao.ProductCategoryDAO;
import local.diplom.service.model.ProductCategory;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by david on 27.02.16.
 */
@Path("productcategory")
@Produces(MediaType.APPLICATION_JSON)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductCategoryController {
    @Inject
    private ProductCategoryDAO productCategoryDAO;

    @GET
    @Path("/{id}")
    public ProductCategory getById(@PathParam("id") Long id) {
        return productCategoryDAO.findById(id);
    }

    @GET
    public List<ProductCategory> getAll() {
        return productCategoryDAO.findAll();
    }

    @POST
    public void post(ProductCategory ProductCategory) throws Exception {
        productCategoryDAO.insert(ProductCategory);
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Long id) throws Exception {
        productCategoryDAO.deleteById(id);
    }
}
