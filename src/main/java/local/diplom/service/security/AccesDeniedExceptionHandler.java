package local.diplom.service.security;

import local.diplom.service.common.Error;

import javax.ejb.EJBAccessException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created  by david on 09.04.16
 */
@Provider
public class AccesDeniedExceptionHandler implements ExceptionMapper<EJBAccessException> {
    public Response toResponse(EJBAccessException exception) {
        return Response.status(403).entity(new Error("not authorized to access this resource"))
                .type(MediaType.APPLICATION_JSON).build();
    }
}
