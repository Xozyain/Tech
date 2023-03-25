package dao;

import models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSession;

import java.util.List;
public class CatDao {
    public Cat findById(int id) {
        return HibernateSession.getSessionFactory().openSession().get(Cat.class, id);
    }

    public Long save(Cat cat) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(cat);
        tx1.commit();
        session.close();
        return cat.getId();
    }

    public void update(Cat cat) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(cat);
        tx1.commit();
        session.close();
    }

    public void delete(Cat cat) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(cat);
        tx1.commit();
        session.close();
    }

    public Cat findCat(Long id) {
        return HibernateSession.getSessionFactory().openSession().get(Cat.class, id);
    }

    public static List<Cat> getAll() {
        List<Cat> cats = (List<Cat>)  HibernateSession.getSessionFactory().openSession().createQuery("From Cat").list();
        return cats;
    }
}
