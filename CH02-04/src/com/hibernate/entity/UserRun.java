 package com.hibernate.entity;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.util.HibernateUtil;

public class UserRun {
     public void saveUser(User user) {
         Session session = null;
         Transaction tran = null;

         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();

             session.save(user);

             tran.commit();

         } catch (Exception e) {
             tran.rollback();
         } finally {
             session.close();
         }
     }

     public User getUser(String id) {
         Session session = null;
         Transaction tran = null;
         User user = null;
         
         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();

             user = session.get(User.class, id);
             tran.commit();

         } catch (Exception e) {
             tran.rollback();
         } finally {
             session.close();
         }
         
         return user;
     }
     
     public void UpdateUser(User user) {
         Session session = null;
         Transaction tran = null;

         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();

             session.update(user);

             tran.commit();

         } catch (Exception e) {
             tran.rollback();
         } finally {
             session.close();
         }
     }
     
     public void deleteUser(String id) {
         Session session = null;
         Transaction tran = null;
         
         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();
             User user = session.get(User.class,id);
             session.delete(user);
             tran.commit();

         } catch (Exception e) {
             tran.rollback();
         } finally {
             session.close();
         }
     }
     
     public List<User> getUsers(){
         Session session = null;
         Transaction tran = null;
         List<User> users = null;
         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();
             Query query =session.createQuery("from User",User.class);
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
