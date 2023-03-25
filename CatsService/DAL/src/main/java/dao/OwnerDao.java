package dao;

import models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSession;
import java.util.List;

public class OwnerDao {
    public Owner findById(int id) {
        return HibernateSession.getSessionFactory().openSession().get(Owner.class, id);
    }

    public void save(Owner owner) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(owner);
        tx1.commit();
        session.close();
    }

    public void update(Owner owner) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(owner);
        tx1.commit();
        session.close();
    }

    public void delete(Owner owner) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(owner);
        tx1.commit();
        session.close();
    }

    public Owner findOwner(long id) {
        return HibernateSession.getSessionFactory().openSession().get(Owner.class, id);
    }


    // Переделать
    public List<Owner> findAll() {
        List<Owner> Owners = (List<Owner>)  HibernateSession.getSessionFactory().openSession().createQuery("From Owner").list();
        return Owners;
    }
}
