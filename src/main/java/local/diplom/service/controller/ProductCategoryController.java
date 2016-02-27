package local.diplom.service.controller;

import com.fasterxml.jackson.databind.JsonNode;
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

    @PUT
    @Path("/{id}")
    public void put(@PathParam("id") Long id, JsonNode jsonNode) throws Exception {
        productCategoryDAO.update(id, jsonNode);
    }


    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Long id) throws Exception {
        productCategoryDAO.deleteById(id);
    }
}
