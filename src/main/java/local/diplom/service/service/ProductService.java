package local.diplom.service.service;

import local.diplom.service.abstracts.AbstractDAO;
import local.diplom.service.model.Product;
import local.diplom.service.model.SaleProduct;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by david on 22.02.16 .
 */

@ApplicationScoped
public class ProductService extends AbstractDAO<Product> {
    public ProductService() {
        super("Product", Product.class);
    }

    public void sell(long productId) throws Exception {
        Product product = findById(productId);
        if (product == null) {
            throw exception(Response.Status.NOT_ACCEPTABLE, "Товара нет в наличаи");
        }
        if (product.getAmount() == 0) {
            throw exception(Response.Status.NOT_ACCEPTABLE, "Товара нет в наличаи");
        }
        try {
            utx.begin();
            SaleProduct saleProduct = new SaleProduct();
            saleProduct.setProductId(product.getId());
            if (product.getCategory().getMarkup() != 0) {
                saleProduct.setCost(product.getCost() + (product.getCost() * product.getCategory().getMarkup() / 100));
            } else {
                saleProduct.setCost(product.getCost());
            }
            em.persist(saleProduct);
            product.setAmount(product.getAmount() - 1);
            em.merge(product);
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
            e.printStackTrace();
            throw exception(Response.Status.SERVICE_UNAVAILABLE, "Что-то пошло не так");
        }

    }
}
