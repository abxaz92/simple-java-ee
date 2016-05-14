package local.diplom.service.controller;

import local.diplom.service.model.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.rowset.serial.SerialBlob;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Blob;
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

    @GET
    @Path("{id}")
    public Response getImage(@PathParam("id") String id) throws SQLException {
        Image image = em.find(Image.class, Long.parseLong(id));
        log.info("{}", image.getId());
        Blob blob = new SerialBlob(image.getImageFile());
        return Response.ok(image.getImageFile()).header("Content-Type", "image/png").build();
    }
}
