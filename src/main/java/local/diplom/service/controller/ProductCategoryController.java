package local.diplom.service.controller;

import com.fasterxml.jackson.databind.JsonNode;
import local.diplom.service.service.ProductCategoryService;
import local.diplom.service.model.ProductCategory;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Контроллер категорий продуктов
 */
@Path("productcategory") // Путь на сайте
@Produces(MediaType.APPLICATION_JSON) // тип возврщаемых данных JSON
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductCategoryController {
    @Inject
    // Подключение к таблице категорий продуктов
    private ProductCategoryService productCategoryService;

    @GET // Тип запроса GET
    @Path("/{id}")
    // Метод получения Категории по id
    public ProductCategory getById(@PathParam("id") Long id) {
        return productCategoryService.findById(id);
    }

    @GET
    // метод получения списка всех категорий
    public List<ProductCategory> getAll() {
        return productCategoryService.findAll();
    }

    @POST // Тип запроса POST(добавление)
    // Добавление категории в БД
    public void post(ProductCategory ProductCategory) throws Exception {
        productCategoryService.insert(ProductCategory);
    }

    @PUT // Тип запроса PUT(изменение)
    @Path("/{id}")
    // Метод обновления сущности в БД
    public void put(@PathParam("id") Long id, JsonNode jsonNode) throws Exception {
        productCategoryService.update(id, jsonNode);
    }


    @DELETE // Тип запроса DELETE(удаление)
    @Path("/{id}")
    // метод удаления сущности из БД
    public void deleteById(@PathParam("id") Long id) throws Exception {
        productCategoryService.deleteById(id);
    }
}
