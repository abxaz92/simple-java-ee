package local.diplom.service.controller;

import local.diplom.service.common.ContextService;
import local.diplom.service.service.SaleProductService;
import local.diplom.service.model.SaleProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created  by david on 06.03.16
 */
@Path("/secure/sale")
@Produces(MediaType.APPLICATION_JSON)
@TransactionManagement(TransactionManagementType.CONTAINER)
@RolesAllowed({"ADMIN"})
public class SaleProductController {
    private static final Logger LOG = LoggerFactory.getLogger(SaleProductController.class);

    @Inject
    private ContextService ctx;

    @Inject
    private SaleProductService saleProductService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"SELLER"})
    public SaleProduct getById(@PathParam("id") Long id) {
        return saleProductService.findById(id);
    }

    @GET
    public List<SaleProduct> getAll() {
        return saleProductService.findAll();
    }

    @POST
    public void post(SaleProduct saleProduct) throws Exception {
        saleProductService.insert(saleProduct);
    }

}
