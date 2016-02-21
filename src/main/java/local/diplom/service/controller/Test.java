package local.diplom.service.controller;

import local.diplom.service.model.Product;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created  by david on 21.02.16
 */

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public class Test {

    @GET
    public Product test() {
        Product product = new Product();
        product.setId(111111L);
        product.setName("aaaaa");
        return product;
    }
}
