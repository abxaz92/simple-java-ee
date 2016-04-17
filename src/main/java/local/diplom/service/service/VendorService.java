package local.diplom.service.service;

import local.diplom.service.abstracts.AbstractDAO;
import local.diplom.service.model.Vendor;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created  by david on 17.04.16
 */
@ApplicationScoped
public class VendorService extends AbstractDAO<Vendor> {

    public VendorService() {
        super(Vendor.class);
    }

    public List<Vendor> findAll() {
        TypedQuery<Vendor> namedQuery = em.createNamedQuery("Vendor.getAll", Vendor.class);
        return namedQuery.getResultList();
    }
}
