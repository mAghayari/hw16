package dao;

import model.customer.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDao {
    SessionFactory sessionFactory = new Configuration().configure("./dao/hibernate.cfg.xml").buildSessionFactory();
    private int customerCounter = 0;

    public void addCustomer(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }

    public Customer fetchCustomer(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("From Customer c Where c.userName = :userName And c.password= :password");
        query.setParameter("userName", customer.getUserName());
        query.setParameter("password", customer.getPassword());
        customer = (Customer) query.uniqueResult();
        transaction.commit();
        session.close();
        return customer;
    }

    public List<Customer> fetchAllCustomers() {
        List<Customer> customers;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("From Customer");
        customers = query.list();
        transaction.commit();
        session.close();
        customerCounter = customers.size();
        return customers;
    }

    public int getCustomerCounter() {
        return customerCounter;
    }
}