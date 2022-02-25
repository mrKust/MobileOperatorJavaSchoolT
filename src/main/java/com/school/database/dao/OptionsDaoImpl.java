package com.school.database.dao;

import com.school.database.entity.Options;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class OptionsDaoImpl implements Dao<Options> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Options get(int id) {
        Options options = null;

        try (Session session = sessionFactory.openSession()){

            options = session.get(Options.class, id);

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return options;
    }

    @Override
    public List<Options> getAll() {
        List<Options> result = null;

        try (Session session = sessionFactory.openSession()) {

            Query query = session.createQuery("from Options ", Options.class);
            result = query.getResultList();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void save(Options options) {
        try (Session session = sessionFactory.openSession()) {

            session.saveOrUpdate(options);

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    /*@Override
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
