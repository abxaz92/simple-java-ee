package local.diplom.service.controller;

import com.fasterxml.jackson.databind.JsonNode;
import local.diplom.service.service.ProductService;
import local.diplom.service.model.Product;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created  by david on 21.02.16
 */

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductController {

    @Inject
    private ProductService productService;

    @GET
    @Path("/{id}")
    public Product getById(@PathParam("id") Long id) {
        return productService.findById(id);
    }

    @GET
    public List<Product> getAll(@QueryParam("skip") Integer skip,
                                @QueryParam("limit") Integer limit,
                                @QueryParam("categoryId") Long categoryId) {
        return productService.findAll(skip, limit, categoryId);
    }

    @POST
    public void post(Product product) throws Exception {
        productService.insert(product);
    }

    @POST
    @Path("/sell/{productId}")
    public void sell(@PathParam("productId") Long productId) throws Exception {
        productService.sell(productId);
    }

    @PUT
    @Path("/{id}")
    public void put(@PathParam("id") Long id, JsonNode jsonNode) throws Exception {
        productService.update(id, jsonNode);
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Long id) throws Exception {
        productService.deleteById(id);
    }
}
