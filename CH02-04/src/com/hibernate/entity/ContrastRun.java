 package com.hibernate.entity;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.util.HibernateUtil;

public class ContrastRun {
    
    public Contrast getContrastByRealName(String realName){
        Session session = null;
        Transaction tran = null;
        Contrast contrast = null;

        try {
            session = HibernateUtil.openSession();
            tran = session.beginTransaction();
            String sql = "select * from contrast where realname = '"+realName+"'";
            contrast = (Contrast)session.createSQLQuery(sql).addEntity(Contrast.class).getResultList().get(0);
            tran.commit();

        } catch (Exception e) {
            tran.rollback();
        } finally {
            session.close();
        }

        return contrast;
    }
    
     public void save(Contrast Contrast) {
         Session session = null;
         Transaction tran = null;

         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();

             session.save(Contrast);

             tran.commit();

         } catch (Exception e) {
             tran.rollback();
         } finally {
             session.close();
         }
     }

     public Contrast get(String id) {
         Session session = null;
         Transaction tran = null;
         Contrast Contrast = null;

         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();

             Contrast = session.get(Contrast.class, id);
             tran.commit();

         } catch (Exception e) {
             tran.rollback();
         } finally {
             session.close();
         }

         return Contrast;
     }

     public void Update(Contrast Contrast) {
         Session session = null;
         Transaction tran = null;

         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();

             session.update(Contrast);

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
             Contrast Contrast = session.get(Contrast.class, id);
             session.delete(Contrast);
             tran.commit();

         } catch (Exception e) {
             tran.rollback();
         } finally {
             session.close();
         }
     }
     
     public List<Contrast> gets(){
         Session session = null;
         Transaction tran = null;
         List<Contrast> contrasts = null;
         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();
             Query query =session.createQuery("from Contrast",Contrast.class);
             contrasts = query.getResultList();
             tran.commit();

         } catch (Exception e) {
             tran.rollback();
         } finally {
             session.close();
         }
         
         return contrasts;
     }
}
