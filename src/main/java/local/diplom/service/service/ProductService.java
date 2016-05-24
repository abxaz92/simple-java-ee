package local.diplom.service.service;

import local.diplom.service.abstracts.AbstractDAO;
import local.diplom.service.controller.SaleProductController;
import local.diplom.service.model.Image;
import local.diplom.service.model.Product;
import local.diplom.service.model.SaleProduct;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by david on 22.02.16 .
 */

@ApplicationScoped
public class ProductService extends AbstractDAO<Product> {
    private static final Logger log = LoggerFactory.getLogger(SaleProductController.class);

    public ProductService() {
        super("Product", Product.class);
    }

    public void uploadImage(@PathParam("id") Long id, MultipartFormDataInput input) throws Exception {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        uploadForm.forEach((key, val) -> {
            InputPart inputPart = val.get(0);
            try (InputStream inputStream = inputPart.getBody(InputStream.class, null)) {
                Product product = findById(id);
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[16384];
                while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                Image image = null;
                if (product.getImage() != null && !product.getImage().isEmpty()) {
                    image = em.find(Image.class, Long.parseLong(product.getImage()));
                }
                try {
                    utx.begin();
                    if (image == null) {
                        image = new Image();
                        image.setImageFile(buffer.toByteArray());
                        em.persist(image);
                    } else {
                        image.setImageFile(buffer.toByteArray());
                        em.merge(image);
                    }
                    product.setImage(String.valueOf(image.getId()));
                    em.merge(product);
                    utx.commit();
                } catch (Exception e) {
                    utx.rollback();
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw exception(Response.Status.SERVICE_UNAVAILABLE, "Что-то пошло не так");
            }

        });
    }

    public Object findAll(Integer skip, Integer limit, String count, Long category) {
        if (count == null) {
            Query query = null;
            if (category == null)
                query = em.createNamedQuery(tablename + ".getAll", Product.class);
            else {
                query = em.createQuery("SELECT p FROM " + tablename + " p WHERE amount > 0 AND category_id = " + category );
            }
            if (skip != null)
                query.setFirstResult(skip);
            if (limit != null)
                query.setMaxResults(limit);
            return query.getResultList();
        } else {
            String sql = "SELECT COUNT(d) FROM " + tablename + " d";
            Query query = em.createQuery(sql);
            return query.getSingleResult();
        }
    }

    @Override
    public void deleteById(Long id) throws Exception {
        try {
            utx.begin();
            Product product = findById(id);
            em.remove(product);
            if (product.getImage() != null) {
                try {
                    Image image = em.find(Image.class, Long.parseLong(product.getImage()));
                    if (image != null)
                        em.remove(image);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            utx.rollback();
        }
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
