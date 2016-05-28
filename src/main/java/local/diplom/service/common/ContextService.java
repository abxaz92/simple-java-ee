package local.diplom.service.common;

import local.diplom.service.service.UsersService;
import local.diplom.service.model.User;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

/**
 * Сервис для получения текущего пользователя
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ContextService {

    @Resource
    private SessionContext context; // контекст пользователя
    @Inject
    private UsersService usersService; // Сервис для получения по id пользователя из базы данных

    // метод для получения имени текущего пользователя
    public String getCurrentUserName() {
        return context.getCallerPrincipal().getName();
    }

    // проверка является ли пользователь админом
    public boolean isCurrentuserAdmin() {
        return context.isCallerInRole("ADMIN");
    }

    // проверка принадлежности пользователя к роли
    public boolean isCurrentuserInRole(String roleName) {
        return context.isCallerInRole(roleName);
    }

    // метод для получения сущности текущего пользователя
    public User getCurrentUser() {
        return usersService.findById(getCurrentUserName());
    }
}
