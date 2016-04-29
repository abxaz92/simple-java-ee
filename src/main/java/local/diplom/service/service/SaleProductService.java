package local.diplom.service.service;

import local.diplom.service.abstracts.AbstractDAO;
import local.diplom.service.model.SaleProduct;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.List;

/**
 * Created  by david on 06.03.16
 */
@ApplicationScoped
public class SaleProductService extends AbstractDAO<SaleProduct> {
    public SaleProductService() {
        super("SaleProduct", SaleProduct.class);
    }
}