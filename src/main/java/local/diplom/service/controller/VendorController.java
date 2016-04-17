package local.diplom.service.controller;

import com.fasterxml.jackson.databind.JsonNode;
import local.diplom.service.model.Vendor;
import local.diplom.service.service.VendorService;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created  by david on 17.04.16
 */
@Path("vendor")
@Produces(MediaType.APPLICATION_JSON)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class VendorController {
    @Inject
    private VendorService vendorService;

    @GET
    @Path("/{id}")
    public Vendor getById(@PathParam("id") Long id) {
        return vendorService.findById(id);
    }

    @GET
    public List<Vendor> getAll() {
        return vendorService.findAll();
    }

    @POST
    public void post(Vendor Vendor) throws Exception {
        vendorService.insert(Vendor);
    }

    @PUT
    @Path("/{id}")
    public void put(@PathParam("id") Long id, JsonNode jsonNode) throws Exception {
        vendorService.update(id, jsonNode);
    }


    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Long id) throws Exception {
        vendorService.deleteById(id);
    }
}