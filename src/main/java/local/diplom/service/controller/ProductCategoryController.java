package local.diplom.service.controller;

import com.fasterxml.jackson.databind.JsonNode;
import local.diplom.service.dao.ProductCategoryService;
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
    private ProductCategoryService productCategoryService;

    @GET
    @Path("/{id}")
    public ProductCategory getById(@PathParam("id") Long id) {
        return productCategoryService.findById(id);
    }

    @GET
    public List<ProductCategory> getAll() {
        return productCategoryService.findAll();
    }

    @POST
    public void post(ProductCategory ProductCategory) throws Exception {
        productCategoryService.insert(ProductCategory);
    }

    @PUT
    @Path("/{id}")
    public void put(@PathParam("id") Long id, JsonNode jsonNode) throws Exception {
        productCategoryService.update(id, jsonNode);
    }


    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Long id) throws Exception {
        productCategoryService.deleteById(id);
    }
}
