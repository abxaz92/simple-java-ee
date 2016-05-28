package local.diplom.service.service;

import local.diplom.service.abstracts.AbstractDAO;
import local.diplom.service.model.SaleProduct;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.List;

/**
 * Сервис для отпущенных продуктов
 */
@ApplicationScoped
public class SaleProductService extends AbstractDAO<SaleProduct> {
    /**
     * Инициализация от каркаса работы с БД
     * при инициализации указываем название таблицы и
     * сущность которую БД должна возвращать
     */
    public SaleProductService() {
        super("SaleProduct", SaleProduct.class);
    }
}
