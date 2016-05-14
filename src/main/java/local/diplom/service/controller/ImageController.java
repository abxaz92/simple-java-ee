package local.diplom.service.controller;

import local.diplom.service.model.Image;
import local.diplom.service.service.ProductService;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.sql.SQLException;

/**
 * Created by david on 14.05.16.
 */
@Path("/image")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ImageController {
    private static final Logger log = LoggerFactory.getLogger(SaleProductController.class);

    @PersistenceContext
    protected EntityManager em;
    @Inject
    private ProductService productService;

    @GET
    @Path("{id}")
    public Response getImage(@PathParam("id") String id) throws SQLException {
        Image image = em.find(Image.class, Long.parseLong(id));
        if (image == null)
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok(image.getImageFile()).header("Content-Type", "image/png").build();
    }

    @POST
    @Path("/{id}")
    @Consumes("multipart/form-data")
    public void uploadImage(@PathParam("id") Long id, MultipartFormDataInput input) throws Exception {
        productService.uploadImage(id, input);
        log.info("uploaded {}", id);
    }
}
