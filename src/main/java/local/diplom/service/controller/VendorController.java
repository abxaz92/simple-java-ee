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
 * Контроллер производителей
 */
@Path("vendor") // Путь на сайте
@Produces(MediaType.APPLICATION_JSON) // тип возврщаемых данных JSON
@TransactionManagement(TransactionManagementType.CONTAINER)
public class VendorController {
    @Inject
    // Подключение к таблице производителей
    private VendorService vendorService;

    @GET // Тип запроса GET
    @Path("/{id}")
    // Метод получения сущности по id
    public Vendor getById(@PathParam("id") Long id) {
        return vendorService.findById(id);
    }

    @GET // Тип запроса GET
    // метод получения списка сощностей
    public List<Vendor> getAll() {
        return vendorService.findAll();
    }

    @POST // Тип запроса POST(добавление)
    // добавление сущности в БД
    public void post(Vendor Vendor) throws Exception {
        vendorService.insert(Vendor);
    }

    @PUT // Тип запроса PUT(изменение)
    @Path("/{id}")
    // Метод обновления сущности в БД
    public void put(@PathParam("id") Long id, JsonNode jsonNode) throws Exception {
        vendorService.update(id, jsonNode);
    }


    @DELETE // Тип запроса DELETE(удаление)
    @Path("/{id}")
    // метод удаления сущности из БД
    public void deleteById(@PathParam("id") Long id) throws Exception {
        vendorService.deleteById(id);
    }
}