package local.diplom.service.controller;

import com.fasterxml.jackson.databind.JsonNode;
import local.diplom.service.common.ContextService;
import local.diplom.service.dao.UsersService;
import local.diplom.service.model.User;

import javax.annotation.security.RolesAllowed;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created  by david on 09.04.16
 */
@Path("/secure/user")
@Produces(MediaType.APPLICATION_JSON)
@TransactionManagement(TransactionManagementType.CONTAINER)
@RolesAllowed({"ADMIN"})
public class UserController {
    @Inject
    private ContextService ctx;

    @Inject
    private UsersService userService;

    @GET
    @Path("/{id}")
    public User getById(@PathParam("id") String id) {
        return userService.findById(id);
    }

    @GET
    public List<User> getAll(@QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit) {
        return userService.findAll(skip, limit);
    }

    @POST
    public void post(User user) throws Exception {
        userService.insert(user);
    }

    @PUT
    @Path("/{id}")
    public void put(@PathParam("id") String id, JsonNode jsonNode) throws Exception {
        userService.update(id, jsonNode);
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") String id) throws Exception {
        userService.deleteById(id);
    }
}
