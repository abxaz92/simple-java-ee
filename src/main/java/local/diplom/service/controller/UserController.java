package local.diplom.service.controller;

import com.fasterxml.jackson.databind.JsonNode;
import local.diplom.service.common.ContextService;
import local.diplom.service.service.UsersService;
import local.diplom.service.model.User;

import javax.annotation.security.RolesAllowed;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Контроллер пользователей
 */
@Path("/secure/user") // Путь на сайте
@Produces(MediaType.APPLICATION_JSON) // тип возврщаемых данных JSON
@TransactionManagement(TransactionManagementType.CONTAINER)
@RolesAllowed({"ADMIN"}) // Роли которым разрешен доступ к этому котролеру
public class UserController {
    @Inject
    // контекс пользователя
    private ContextService ctx;

    @Inject
    // Подключение к таблице проданных пользователей
    private UsersService userService;

    @GET // Тип запроса GET
    @Path("/{id}")
    // Метод получения сущности по id
    public User getById(@PathParam("id") String id) {
        return userService.findById(id);
    }

    @GET // Тип запроса GET
    // метод получения списка сощностей
    public List<User> getAll(@QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit) {
        return userService.findAll(skip, limit);
    }

    @POST // Тип запроса POST(добавление)
    // добавление сущности в БД
    public void post(User user) throws Exception {
        userService.insert(user);
    }

    @PUT // Тип запроса PUT(изменение)
    @Path("/{id}")
    // Метод обновления сущности в БД
    public void put(@PathParam("id") String id, JsonNode jsonNode) throws Exception {
        userService.update(id, jsonNode);
    }

    @DELETE // Тип запроса DELETE(удаление)
    @Path("/{id}")
    // метод удаления сущности из БД
    public void deleteById(@PathParam("id") String id) throws Exception {
        userService.deleteById(id);
    }
}
