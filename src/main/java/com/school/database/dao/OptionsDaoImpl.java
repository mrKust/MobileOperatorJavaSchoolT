package com.school.database.dao;

import com.school.database.entity.Options;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
public class OptionsDaoImpl implements Dao<Options> {

    @Autowired
    private SessionFactory sessionFactory;

    /*@Override
    public Options get(int id) {
        Options options = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            options = session.get(Options.class, id);
            if (options != null)
                return options;
            else System.out.println("Options with this id not found");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    @Override
    public List<Options> getAll() {
        List<Options> result = null;

        try (Session session = sessionFactory.openSession()) {

            Query query = session.createQuery("from Options ", Options.class);
            result = query.getResultList();

            if (result.size() != 0)
                return result;
            else System.out.println("No such elements");

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*@Override
    public void save(Options options) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Integer id =(Integer)session.save(options);
            System.out.println("Option is created  with Id::"+id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Options options, String[] params) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            if(options != null){
                options.setOptionsName(params[0]);
                options.setPrice(Integer.parseInt(params[1]));
                options.setCostToAdd(Integer.parseInt(params[2]));
                options.setAvailableOptionToConnectOrNot(Boolean.parseBoolean(params[3]));

                session.beginTransaction();
                session.update(options);
                session.getTransaction().commit();
            }else{
                System.out.println("Employee doesn't exist with provideded Id..");
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int optionsId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Options options = session.get(Options.class, optionsId);
            if(options != null){
                session.beginTransaction();

                session.delete(options);
                session.getTransaction().commit();
            }else{
                System.out.println("Options doesn't exist with provideded Id..");
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }*/

}
