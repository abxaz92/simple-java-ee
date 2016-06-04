package local.diplom.service.controller;

import local.diplom.service.common.ContextService;
import local.diplom.service.model.Product;
import local.diplom.service.service.ProductService;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.IntStream;

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

    @Inject
    private ProductService productService;
    private static final Random RAND = new Random();
    private static final Map<Integer, String> ADDR = new HashMap();


    private static final Map<Integer, String> FIO = new HashMap();
    private static final Map<Integer, String> PHONE = new HashMap();

    static {
        ADDR.put(0, "Коста 278");
        ADDR.put(1, "Ленина 23");
        ADDR.put(2, "Мира 20");
        ADDR.put(3, "Штыба 14");
        ADDR.put(4, "Плиева 15");

        PHONE.put(0, "12345678912");
        PHONE.put(1, "12345678913");
        PHONE.put(2, "12345678914");
        PHONE.put(3, "12345678915");
        PHONE.put(4, "12345678916");

        FIO.put(0, "Антонов Александр");
        FIO.put(1, "Петров Василий");
        FIO.put(2, "Смирнов Иван");
        FIO.put(3, "Иванов Николай");
        FIO.put(4, "Иванов Василий");
    }

    @PUT
    @Path("/generate/{amount}")
    public void generateData(@PathParam("amount") Integer amount) {
        List<Product> products = (List<Product>) productService.findAll(null, null, null);
        IntStream.range(0, amount).forEach(value -> {
            int randomNum = RAND.nextInt(products.size());
            int randomPhone = RAND.nextInt(5);
            int randomAddr = RAND.nextInt(5);
            int randomDay = RAND.nextInt(28) + 1;
            int randomMonth = RAND.nextInt(6 - 4 + 1) + 4;
            LocalDate date = LocalDate.of(2016, randomMonth, randomDay);
            SaleProduct saleProduct = new SaleProduct();
            Product product = products.get(randomNum);
            saleProduct.setProductId(product.getId());
            saleProduct.setCost(product.getCost());
            saleProduct.setFio(FIO.get(randomPhone));
            saleProduct.setPhone(PHONE.get(randomPhone));
            saleProduct.setAddress(ADDR.get(randomAddr));
            Instant instant = date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            saleProduct.setDate(Date.from(instant).getTime());
            LOG.info("{} : {} ", value, date);
            try {
                saleProductService.insert(saleProduct);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
