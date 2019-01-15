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

    public Space getCrk(String cRk){
         Session session = null;
         Transaction tran = null;
         Space space = null;

         try {
             session = HibernateUtil.openSession();
             tran = session.beginTransaction();
             String sql = "SELECT "
                 + "s.id,"
                 + "s.x,"
                 + "s.y,"
                 + "s.z,"
                 + "s.nature,"
                 + "s.carid "
                 + "FROM space s,contrast c "
                 + "WHERE "
                 + "s.nature = c.id "
                 + "AND "
                 + "c.realname = '"
                 + cRk
                 + "'";
             System.out.println(sql);
             space = (Space)session.createSQLQuery(sql).addEntity(Space.class).getResultList().get(0);
             tran.commit();

         } catch (Exception e) {
             tran.rollback();
         } finally {
             session.close();
         }

         return space;
     }
    
    public Space getSpaceByXYZ(int x,int y,int z){
        Session session = null;
        Transaction tran = null;
        Space space = null;
        String sql = "select * from space where x = "+x+" and y = "+y+" and z = "+z;
        try {
            session = HibernateUtil.openSession();
            tran = session.beginTransaction();
            space = (Space)session.createSQLQuery(sql).addEntity(Space.class).getResultList().get(0);
            tran.commit();

        } catch (Exception e) {
            tran.rollback();
        } finally {
            session.close();
        }

        return space;
    }
    
    public Space getSaveSpace(){
        Session session = null;
        Transaction tran = null;
        Space space = null;

        try {
            session = HibernateUtil.openSession();
            tran = session.beginTransaction();
            String sql = "select * "
                + "from space "
                + "where "
                + "nature = 'aaaeeb3acc5e44899a5d1de5ca5ab11a' "
                + "ORDER BY z,y,x DESC";
            space = (Space)session.createSQLQuery(sql).addEntity(Space.class).getResultList().get(0);
            tran.commit();

        } catch (Exception e) {
            tran.rollback();
        } finally {
            session.close();
        }

        return space;
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

    public List<Space> gets() {
        Session session = null;
        Transaction tran = null;
        List<Space> spaces = null;
        try {
            session = HibernateUtil.openSession();
            tran = session.beginTransaction();
            Query query = session.createQuery("from Space", Space.class);
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
