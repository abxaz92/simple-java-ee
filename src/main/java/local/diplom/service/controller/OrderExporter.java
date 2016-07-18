package local.diplom.service.controller;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import local.diplom.service.model.TaxiOrder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by [david] on 18.07.16.
 */
@Path("/order/export")
public class OrderExporter {
    private static final Logger log = LoggerFactory.getLogger(OrderExporter.class);
    @Inject
    private Mongo mongo;
    @PersistenceContext
    protected EntityManager em;

    @POST
    public void export() {
        Session session = em.unwrap(Session.class);
        Transaction tx = session.getTransaction();
        tx.begin();
        DBCollection collection = mongo.getDB("taxi").getCollection("order");
        int batchSize = 200;
        try (DBCursor cursor = collection.find()) {
            cursor.skip(0);
            cursor.limit(1000);
            log.info("{}", cursor.count());
            Iterator<DBObject> iterator = cursor.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                TaxiOrder order = new TaxiOrder();
                DBObject obj = iterator.next();
                i++;
                order.setId(obj.get("_id").toString());
                log.info("{}", obj.get("date"));
                log.info("{}", obj.get("dateArrrive"));
                order.setDate(new Date(obj.get("date").toString()));
                order.setStatus(obj.get("status").toString());
                order.setPhone(obj.get("phone").toString());
                order.setDescription(obj.get("description").toString());
                order.setDriver(obj.get("driver").toString());
                order.setDateArrrive(new Date(obj.get("dateArrrive").toString()));
                order.setDateComplete(new Date(obj.get("dateComplete").toString()));
                session.persist(order);
                if (i % batchSize == 0) {
                    session.flush();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        session.close();
    }


}
