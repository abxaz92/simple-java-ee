package local.diplom.service.common;

import local.diplom.service.dao.UsersService;
import local.diplom.service.model.User;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

/**
 * Created  by david on 09.04.16
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ContextService {

    @Resource
    private SessionContext context;
    @Inject
    private UsersService usersService;
    public String getCurrentUserName() {
        return context.getCallerPrincipal().getName();
    }

    public boolean isCurrentuserAdmin() {
        return context.isCallerInRole("ADMIN");
    }

    public boolean isCurrentuserInRole(String roleName) {
        return context.isCallerInRole(roleName);
    }

    public User getCurrentUser() {
        return usersService.findById(getCurrentUserName());
    }
}
