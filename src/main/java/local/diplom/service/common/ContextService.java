package local.diplom.service.common;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 * Created  by david on 09.04.16
 */
@Stateless
public class ContextService {

    @Resource
    private SessionContext context;

    public String getCurrentUserName() {
        return context.getCallerPrincipal().getName();
    }

    public boolean isCurrentuserAdmin() {
        return context.isCallerInRole("ADMIN");
    }

    public boolean isCurrentuserInRole(String roleName) {
        return context.isCallerInRole(roleName);
    }
}
