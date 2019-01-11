 package com.hibernate.entity;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.util.HibernateUtil;

public class SpaceRun {
     public void save(Space space) {
         Session session = null;
         Transaction tran = null;

         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();

             session.save(space);

             tran.commit();

         } catch (Exception e) {
             tran.rollback();
         } finally {
             session.close();
         }
     }

     public Space get(String id) {
         Session session = null;
         Transaction tran = null;
         Space space = null;

         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();

             space = session.get(Space.class, id);
             tran.commit();

         } catch (Exception e) {
             tran.rollback();
         } finally {
             session.close();
         }

         return space;
     }

     public void Update(Space space) {
         Session session = null;
         Transaction tran = null;

         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();

             session.update(space);

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
             Space space = session.get(Space.class, id);
             session.delete(space);
             tran.commit();

         } catch (Exception e) {
             tran.rollback();
         } finally {
             session.close();
         }
     }
     
     public List<Space> gets(){
         Session session = null;
         Transaction tran = null;
         List<Space> spaces = null;
         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();
             Query query =session.createQuery("from Space",Space.class);
             spaces = query.getResultList();
             tran.commit();

         } catch (Exception e) {
             tran.rollback();
         } finally {
             session.close();
         }
         
         return spaces;
     }
}
