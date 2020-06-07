package dao;

import dto.cart.Cart;
import dto.customer.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class CartDao {
    SessionFactory sessionFactory = new Configuration().configure("./dao/hibernate.cfg.xml").buildSessionFactory();

    public void addACart(Cart cart) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(cart);
        transaction.commit();
        session.close();
    }

    public List<Cart> fetChCustomersCarts(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Cart> carts;
        Query query = session.createQuery("From Cart c Where c.customer.id = :customerId");
        query.setParameter("customerId", customer.getId());
        carts = query.list();
        transaction.commit();
        session.close();
        return carts;
    }
}