package com.hibernate.entity;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.util.HibernateUtil;

public class CarRun {

    public void save(Car Car) {
        Session session = null;
        Transaction tran = null;

        try {
            session = HibernateUtil.openSession();
            tran = session.beginTransaction();

            session.save(Car);

            tran.commit();

        } catch (Exception e) {
            tran.rollback();
        } finally {
            session.close();
        }
    }

    public Car get(String id) {
        Session session = null;
        Transaction tran = null;
        Car Car = null;

        try {
            session = HibernateUtil.openSession();
            tran = session.beginTransaction();

            Car = session.get(Car.class, id);
            tran.commit();

        } catch (Exception e) {
            tran.rollback();
        } finally {
            session.close();
        }

        return Car;
    }

    public void Update(Car Car) {
        Session session = null;
        Transaction tran = null;

        try {
            session = HibernateUtil.openSession();
            tran = session.beginTransaction();

            session.update(Car);

            tran.commit();

        } catch (Exception e) {
            tran.rollback();
        } finally {
            session.close();
        }
    }

    public void delete(String id) {
        Session session = null;
        Transaction tran = null;

        try {
            session = HibernateUtil.openSession();
            tran = session.beginTransaction();
            Car Car = session.get(Car.class, id);
            session.delete(Car);
            tran.commit();

        } catch (Exception e) {
            tran.rollback();
        } finally {
            session.close();
        }
    }
    
    public List<Car> gets(){
        Session session = null;
        Transaction tran = null;
        List<Car> users = null;
        try {
            session = HibernateUtil.openSession();
            tran = session.beginTransaction();
            Query query =session.createQuery("from Car",Car.class);
            users = query.getResultList();
            tran.commit();

        } catch (Exception e) {
            tran.rollback();
        } finally {
            session.close();
        }
        
        return users;
    }
}
