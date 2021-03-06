package dao;

import model.admin.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class AdminDao {
    SessionFactory sessionFactory = new Configuration().configure("config/hibernate.cfg.xml").buildSessionFactory();

    public void addAdmin(Admin admin) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(admin);
        transaction.commit();
        session.close();
    }

    public Admin fetchAdmin(Admin admin) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("From Admin a Where a.userName = :userName And a.password= :password");
        query.setParameter("userName", admin.getUserName());
        query.setParameter("password", admin.getPassword());
        admin = (Admin) query.uniqueResult();
        transaction.commit();
        session.close();
        return admin;
    }
}