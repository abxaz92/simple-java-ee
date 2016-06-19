package local.diplom.service.service;

import local.diplom.service.abstracts.AbstractDAO;
import local.diplom.service.model.Orders;
import local.diplom.service.model.ProductCategory;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Order;
import java.util.List;

/**
 * Created by david on 19.06.16.
 */
@ApplicationScoped
public class OrderService extends AbstractDAO<Orders> {
    public OrderService() {
        super("orders", Orders.class);
    }

    public List<Orders> findAll() {
        TypedQuery<Orders> namedQuery = em.createNamedQuery("Orders.getAll", Orders.class);
        return namedQuery.getResultList();
    }

    @Override
    public void deleteById(Long id) throws Exception {
        Orders order = findById(id);
        order.setDone(true);
        update(order);
    }
}
