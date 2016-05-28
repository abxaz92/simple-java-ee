package local.diplom.service.controller;

import local.diplom.service.common.ContextService;
import local.diplom.service.service.SaleProductService;
import local.diplom.service.model.SaleProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Контроллер проданных продуктов
 */
@Path("/secure/sale") // Путь на сайте
@Produces(MediaType.APPLICATION_JSON) // тип возврщаемых данных JSON
@TransactionManagement(TransactionManagementType.CONTAINER)
@RolesAllowed({"ADMIN"}) // Роли которым разрешен доступ к этому котролеру
public class SaleProductController {
    private static final Logger LOG = LoggerFactory.getLogger(SaleProductController.class);

    @Inject
    // контекс пользователя
    private ContextService ctx;

    @Inject
    // Подключение к таблице проданных продуктов
    private SaleProductService saleProductService;

    @GET // Тип запроса GET
    @Path("/{id}")
    @RolesAllowed({"SELLER"})
    // Метод получения сущности по id
    public SaleProduct getById(@PathParam("id") Long id) {
        return saleProductService.findById(id);
    }

    @GET // Тип запроса GET
    public Object getAll(@QueryParam("skip") Integer skip,
                         @QueryParam("limit") Integer limit,
                         @QueryParam("count") String count) {
        return saleProductService.findAll(skip, limit, count);
    }
}
