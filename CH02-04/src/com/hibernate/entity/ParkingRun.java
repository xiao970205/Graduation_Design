 package com.hibernate.entity;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.util.HibernateUtil;

public class ParkingRun {
    public Parking getParkingByCarid(String carId){
        Session session = null;
        Transaction tran = null;
        List<Parking> users = null;
        try {
            session = HibernateUtil.openSession();
            tran = session.beginTransaction();
            Query query =session.createQuery("from Parking where carId = '"+carId+"'",Parking.class);
            users = query.getResultList();
            tran.commit();

        } catch (Exception e) {
            tran.rollback();
        } finally {
            session.close();
        }
        
        return users.get(0);
    }
    
    
    
     public void save(Parking Parking) {
         Session session = null;
         Transaction tran = null;

         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();

             session.save(Parking);

             tran.commit();

         } catch (Exception e) {
             tran.rollback();
         } finally {
             session.close();
         }
     }

     public Parking get(String id) {
         Session session = null;
         Transaction tran = null;
         Parking Parking = null;

         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();

             Parking = session.get(Parking.class, id);
             tran.commit();

         } catch (Exception e) {
             tran.rollback();
         } finally {
             session.close();
         }

         return Parking;
     }

     public void Update(Parking Parking) {
         Session session = null;
         Transaction tran = null;

         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();

             session.update(Parking);

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
             Parking Parking = session.get(Parking.class, id);
             session.delete(Parking);
             tran.commit();

         } catch (Exception e) {
             tran.rollback();
         } finally {
             session.close();
         }
     }
     
     public List<Parking> gets(){
         Session session = null;
         Transaction tran = null;
         List<Parking> users = null;
         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();
             Query query =session.createQuery("from Parking",Parking.class);
             users = query.getResultList();
             tran.commit();

         } catch (Exception e) {
             tran.rollback();
         } finally {
             session.close();
         }
         
         return users;
     }
     
     public List<Parking> getParkingsByNature(String nature){
         Session session = null;
         Transaction tran = null;
         List<Parking> parkings = null;
         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();
             Query query =session.createQuery("from Parking where nature = '"+nature+"'",Parking.class);
             parkings = query.getResultList();
             tran.commit();

         } catch (Exception e) {
             tran.rollback();
         } finally {
             session.close();
         }
         
         return parkings;
     }
}
