package local.diplom.service.dao;

import local.diplom.service.abstracts.AbstractDAO;
import local.diplom.service.model.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by david on 22.02.16 .
 */

@ApplicationScoped
public class ProductDAO extends AbstractDAO<Product> {
    public ProductDAO() {
        super(Product.class);
    }

    public List<Product> findAll() {
        TypedQuery<Product> namedQuery = em.createNamedQuery("Product.getAll", Product.class);
        return namedQuery.getResultList();
    }
}
