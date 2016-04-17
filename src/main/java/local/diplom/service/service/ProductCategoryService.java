package local.diplom.service.service;

import local.diplom.service.abstracts.AbstractDAO;
import local.diplom.service.model.ProductCategory;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by david on 27.02.16.
 */
@ApplicationScoped
public class ProductCategoryService extends AbstractDAO<ProductCategory> {

    public ProductCategoryService() {
        super(ProductCategory.class);
    }

    public List<ProductCategory> findAll() {
        TypedQuery<ProductCategory> namedQuery = em.createNamedQuery("ProductCategory.getAll", ProductCategory.class);
        return namedQuery.getResultList();
    }
}
