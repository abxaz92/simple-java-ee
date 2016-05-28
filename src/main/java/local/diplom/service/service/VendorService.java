package local.diplom.service.service;

import local.diplom.service.abstracts.AbstractDAO;
import local.diplom.service.model.Vendor;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Сервис для призводителей
 */
@ApplicationScoped
public class VendorService extends AbstractDAO<Vendor> {
    /**
     * Инициализация от каркаса работы с БД
     * при инициализации указываем название таблицы и
     * сущность которую БД должна возвращать
     */
    public VendorService() {
        super("Vendor", Vendor.class);
    }

    /**
     * Получение списка отпущенных товаров из БД
     */
    public List<Vendor> findAll() {
        TypedQuery<Vendor> namedQuery = em.createNamedQuery("Vendor.getAll", Vendor.class);
        return namedQuery.getResultList();
    }
}
