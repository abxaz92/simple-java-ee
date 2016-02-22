package local.diplom.service.dao;

import local.diplom.service.abstracts.AbstractDAO;
import local.diplom.service.model.Product;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by david on 22.02.16 .
 */

@ApplicationScoped
public class ProductDAO extends AbstractDAO<Product> {
    public ProductDAO() {
        super(Product.class);
    }
}
