package local.diplom.service.controller;

import com.fasterxml.jackson.databind.JsonNode;
import local.diplom.service.model.SaleProduct;
import local.diplom.service.service.ProductService;
import local.diplom.service.model.Product;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.Lob;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Контроллер продуктов
 */
@Path("/product") // Путь на сайте
@Produces(MediaType.APPLICATION_JSON) // тип возврщаемых данных JSON
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductController {

    @Inject
    // Подключение к таблице продуктов
    private ProductService productService;

    @GET // Тип запроса GET
    @Path("/{id}")
    // Метод получения сущности по id
    public Product getById(@PathParam("id") Long id) {
        return productService.findById(id);
    }

    @GET // Тип запроса GET
    // метод получения списка сощностей
    public Object getAll(@QueryParam("skip") Integer skip,
                         @QueryParam("limit") Integer limit,
                         @QueryParam("count") String count,
                         @QueryParam("category") Long category) {
        return productService.findAll(skip, limit, count, category);
    }

    @POST // Тип запроса POST(добавление)
    // добавление сущности в БД
    public Product post(Product product) throws Exception {
        productService.insert(product);
        return product;
    }

    @POST // Тип запроса POST(добавление)
    @Path("/sell")
    // Метод продажи товара
    public void sell(SaleProduct saleProduct) throws Exception {
        productService.sell(saleProduct);
    }

    @PUT // Тип запроса PUT(изменение)
    @Path("/{id}")
    // Метод обновления сущности в БД
    public void put(@PathParam("id") Long id, JsonNode jsonNode) throws Exception {
        productService.update(id, jsonNode);
    }

    @DELETE // Тип запроса DELETE(удаление)
    @Path("/{id}")
    // метод удаления сущности из БД
    public void deleteById(@PathParam("id") Long id) throws Exception {
        productService.deleteById(id);
    }
}
