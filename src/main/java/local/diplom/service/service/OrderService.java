package local.diplom.service.service;

import local.diplom.service.abstracts.AbstractDAO;
import local.diplom.service.model.Orders;
import local.diplom.service.model.ProductCategory;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Order;
import java.util.List;

/**
 * Created by david on 19.06.16.
 */
@ApplicationScoped
public class OrderService extends AbstractDAO<Orders> {
    @Resource(mappedName = "java:jboss/mail/Default")
    private Session mailSession;

    public OrderService() {
        super("orders", Orders.class);
    }

    public List<Orders> findAll() {
        TypedQuery<Orders> namedQuery = em.createNamedQuery("Orders.getAll", Orders.class);
        return namedQuery.getResultList();
    }

    @Override
    public void deleteById(Long id) throws Exception {
        Orders order = findById(id);
        /*order.setDone(true);
        update(order);*/
        MimeMessage m = new MimeMessage(mailSession);
        Address from = new InternetAddress("fraev.artur@inbox.ru");
        Address[] to = new InternetAddress[]{new InternetAddress(order.getEmail())};

        m.setFrom(from);
        m.setRecipients(Message.RecipientType.TO, to);
        m.setSubject("Заявка на ремонт");
        m.setSentDate(new java.util.Date());
        m.setContent("Ваше оборудование готово", "text/html; charset=windows-1251");
        Transport.send(m);
    }
}
