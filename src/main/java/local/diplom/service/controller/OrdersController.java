package local.diplom.service.controller;

import com.fasterxml.jackson.databind.JsonNode;
import local.diplom.service.model.Orders;
import local.diplom.service.service.OrderService;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.criteria.Order;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by david on 19.06.16.
 */
@Path("orders") // Путь на сайте
@Produces(MediaType.APPLICATION_JSON) // тип возврщаемых данных JSON
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrdersController {

    @Inject
    // Подключение к таблице категорий продуктов
    private OrderService orderService;

    @GET // Тип запроса GET
    @Path("/{id}")
    // Метод получения Категории по id
    public Orders getById(@PathParam("id") Long id) {
        return orderService.findById(id);
    }

    @GET
    // метод получения списка всех категорий
    public List<Orders> getAll() {
        return orderService.findAll();
    }

    @POST // Тип запроса POST(добавление)
    // Добавление категории в БД
    public void post(Orders order) throws Exception {
        orderService.insert(order);
    }

    @PUT // Тип запроса PUT(изменение)
    @Path("/{id}")
    // Метод обновления сущности в БД
    public void put(@PathParam("id") Long id, JsonNode jsonNode) throws Exception {
        orderService.update(id, jsonNode);
    }


    @DELETE // Тип запроса DELETE(удаление)
    @Path("/{id}")
    // метод удаления сущности из БД
    public void deleteById(@PathParam("id") Long id) throws Exception {
        orderService.deleteById(id);
    }

}
