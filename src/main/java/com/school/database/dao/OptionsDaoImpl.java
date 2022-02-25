package com.school.database.dao;

import com.school.database.entity.Options;
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

        Session session = sessionFactory.getCurrentSession();
        options = session.get(Options.class, id);

        return options;
    }

    @Override
    public List<Options> getAll() {
        List<Options> result = null;

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Options ", Options.class);
        result = query.getResultList();

        return result;
    }

    @Override
    public void save(Options options) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(options);

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

    }*/

    @Override
    public void delete(int optionsId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Options " +
                "where id =:optionId");
        query.setParameter("optionId", optionsId);
        query.executeUpdate();

    }

}
