package local.diplom.service.service;

import local.diplom.service.abstracts.AbstractDAO;
import local.diplom.service.controller.SaleProductController;
import local.diplom.service.model.Image;
import local.diplom.service.model.Product;
import local.diplom.service.model.SaleProduct;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Сервис для Продуктов
 */

@ApplicationScoped
public class ProductService extends AbstractDAO<Product> {
    private static final Logger log = LoggerFactory.getLogger(SaleProductController.class);

    /**
     * Инициализация от каркаса работы с БД
     * при инициализации указываем название таблицы и
     * сущность которую БД должна возвращать
     */
    public ProductService() {
        super("Product", Product.class);
    }

    // метод загрузки изображение в БД
    public void uploadImage(@PathParam("id") Long id, MultipartFormDataInput input) throws Exception {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap(); // Получение списка файлов
        uploadForm.forEach((key, val) -> { // Получение списка файлов
            InputPart inputPart = val.get(0); // Получение списка файлов
            try (InputStream inputStream = inputPart.getBody(InputStream.class, null)) {
                Product product = findById(id); // достаем товар по id
                ByteArrayOutputStream buffer = new ByteArrayOutputStream(); // чтение файла в память
                int nRead;
                byte[] data = new byte[16384];
                while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                Image image = null; // инициализации сущности изображения
                if (product.getImage() != null && !product.getImage().isEmpty()) { // если у товара уже есть изображение
                    image = em.find(Image.class, Long.parseLong(product.getImage())); // то достаем его из БД
                }
                try {
                    utx.begin(); // начало транзакции
                    if (image == null) {  // если у товара нет изображения
                        image = new Image(); // создаем новое
                        image.setImageFile(buffer.toByteArray()); // добавляем файл в сущность изображение
                        em.persist(image); // сохраняем изображение в БД
                    } else { // иначе если у товара уже есть изображение
                        image.setImageFile(buffer.toByteArray()); // добавляем файл в сущность изображение
                        em.merge(image); // сохраняем изображение в БД
                    }
                    product.setImage(String.valueOf(image.getId())); // указываем товару ID только что созданного изображения
                    em.merge(product); // сохраняем товар в БД
                    utx.commit(); // завершаем транзакцию
                } catch (Exception e) {
                    utx.rollback(); // в случае ошибки откатываем транзакцию
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace(); // в случае ошибки возвращаем клиенту ошибку
                throw exception(Response.Status.SERVICE_UNAVAILABLE, "Что-то пошло не так");
            }

        });
    }

    // Метод выборки товаров из БД
    public Object findAll(Integer skip, Integer limit, String count, Long category) {
        // Если не запрашивается количество товаров
        if (count == null) {
            Query query = null;
            if (category == null) // если не указан фильтр по категории
                query = em.createNamedQuery(tablename + ".getAll", Product.class); // достаем все товары из БД
            else { // иначе если фильтр по категориям указан, то создаем запрос с категорией
                query = em.createQuery("SELECT p FROM " + tablename + " p WHERE amount > 0 AND category_id = " + category);
            }
            if (skip != null) // если указано с какой позиции вернуть документы
                query.setFirstResult(skip);
            if (limit != null) // если указано сколько документов вернуть
                query.setMaxResults(limit);
            return query.getResultList(); // возвращаем список
        } else {
            String sql = "SELECT COUNT(d) FROM " + tablename + " d"; // если запросили количесво документов в БД
            Query query = em.createQuery(sql); // создаем запрос
            return query.getSingleResult(); // возвращаем ответ
        }
    }

    @Override
    // Удаление товара из БД
    public void deleteById(Long id) throws Exception {
        try {
            utx.begin(); // начало транзакции
            Product product = findById(id); // достаем товар из БД
            em.remove(product); // удаляем товар
            if (product.getImage() != null) { // если в товаре указано изображение, то удаляем его
                try {
                    Image image = em.find(Image.class, Long.parseLong(product.getImage())); // дсотаем изображение из БД
                    if (image != null) // если изображение есть в БД
                        em.remove(image); // удаляем изображение
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            utx.commit(); // завершаем транзакцию
        } catch (Exception e) {
            e.printStackTrace();
            utx.rollback(); // в случае ошибки отказываем транзакицю
        }
    }

    // метод продажи товара
    public void sell(SaleProduct saleProduct) throws Exception {
        Product product = findById(saleProduct.getProductId()); // достаем продаваемы товар
        if (product == null) { // если такого товара нет в бд, то возвращаем ошибку
            throw exception(Response.Status.NOT_ACCEPTABLE, "Товара нет в наличаи");
        }
        if (product.getAmount() == 0) { // если такого товара нет в наличии, то возвращаем ошибку
            throw exception(Response.Status.NOT_ACCEPTABLE, "Товара нет в наличаи");
        }
        try {
            utx.begin(); // начало транзакции
            if (product.getCategory().getMarkup() != 0) { // если у категории есть наценка, то прибавляем ее
                // устанавливаем стоимость товара с учетом наценки
                saleProduct.setCost(product.getCost() + (product.getCost() * product.getCategory().getMarkup() / 100));
            } else {
                // устанавливаем стоимость товара без учета наценки
                saleProduct.setCost(product.getCost());
            }
            saleProduct.setDate(System.currentTimeMillis());
            em.persist(saleProduct); // добавляем в бд документ отпущенного товара
            product.setAmount(product.getAmount() - 1); // уменьшаем число оставшихся в наличии товаров
            em.merge(product); // сохраняем изменения товара в БД
            utx.commit(); // завершаем транзакцию
        } catch (Exception e) {
            utx.rollback(); // в случае ошибки отказываем транзакицю
            e.printStackTrace();
            throw exception(Response.Status.SERVICE_UNAVAILABLE, "Что-то пошло не так");
        }

    }
}
