package local.diplom.service.service;

import local.diplom.service.abstracts.AbstractDAO;
import local.diplom.service.model.ProductCategory;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Сервис для Категорий продуктов
 */
@ApplicationScoped
public class ProductCategoryService extends AbstractDAO<ProductCategory> {

    /**
     * Инициализация от каркаса работы с БД
     * при инициализации указываем название таблицы и
     * сущность которую БД должна возвращать
     */
    public ProductCategoryService() {
        super("ProductCategory", ProductCategory.class);
    }

    /**
     * Получение списка продуктов из БД
     */
    public List<ProductCategory> findAll() {
        TypedQuery<ProductCategory> namedQuery = em.createNamedQuery("ProductCategory.getAll", ProductCategory.class);
        return namedQuery.getResultList();
    }
}
