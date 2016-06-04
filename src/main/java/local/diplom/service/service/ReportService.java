package local.diplom.service.service;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by david on 04.06.16.
 */
@ApplicationScoped
public class ReportService {
    @PersistenceContext
    protected EntityManager em;

    public Object countTotalSumm(Long start, Long end) {
        Query query = em.createNativeQuery("SELECT SUM(cost) AS TotalSum FROM SaleProduct where date BETWEEN :start AND :end ;");
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getSingleResult();
    }

    public Object countAvgSale(Long start, Long end) {
        Query query = em.createNativeQuery("SELECT AVG(cost) AS AvgCost FROM SaleProduct where date BETWEEN :start AND :end ;");
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getSingleResult();
    }

    public Object countMaxSale(Long start, Long end) {
        Query query = em.createNativeQuery("SELECT MAX(cost) AS MaxCost FROM SaleProduct where date BETWEEN :start AND :end ;");
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getSingleResult();
    }

    public Object countSales(Long start, Long end) {
        Query query = em.createNativeQuery("SELECT s.productId as id, p.name, COUNT(s.productId) AS Count\n" +
                "  FROM SaleProduct s\n" +
                "  LEFT JOIN Product p\n" +
                "    ON p.id = s.productId\n" +
                "  where date BETWEEN :start AND :end \n" +
                "  GROUP BY s.productId, p.name\n" +
                "  ORDER BY Count DESC;");
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getResultList();
    }

    public Object countSellers(Long start, Long end) {
        Query query = em.createNativeQuery("  SELECT s.fio as id, s.phone, COUNT(s.phone) AS Count\n" +
                "  FROM SaleProduct s\n" +
                "  where date BETWEEN :start AND :end \n" +
                "  GROUP BY s.phone, fio\n" +
                "  ORDER BY Count DESC;");
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getResultList();
    }

}
