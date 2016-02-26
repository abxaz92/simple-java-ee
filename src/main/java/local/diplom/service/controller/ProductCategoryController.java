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
    private ProductCategoryDAO ProductCategoryDAO;

    @GET
    @Path("/{id}")
    public ProductCategory getById(@PathParam("id") String id) {
        return ProductCategoryDAO.findById(id);
    }

    @GET
    public List<ProductCategory> getAll() {
        return ProductCategoryDAO.findAll();
    }

    @POST
    public void post(ProductCategory ProductCategory) {
        ProductCategoryDAO.insert(ProductCategory);
    }
}
