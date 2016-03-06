package local.diplom.service.dao;

import local.diplom.service.abstracts.AbstractDAO;
import local.diplom.service.model.SaleProduct;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.List;

/**
 * Created  by david on 06.03.16
 */
@ApplicationScoped
public class SaleProductDAO extends AbstractDAO<SaleProduct> {
    public SaleProductDAO() {
        super(SaleProduct.class);
    }

    public List<SaleProduct> findAll() {
        Query query = em.createNamedQuery("complex", SaleProduct.class);
        return query.getResultList();
    }
}
