package com.school.database.dao;

import com.school.database.dao.Dao;
import com.school.database.entity.Tariff;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class TariffDaoImpl implements Dao<Tariff> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Tariff get(int id) {
        Tariff tariff = null;

        Session session = sessionFactory.getCurrentSession();
        tariff = session.get(Tariff.class, id);

        return tariff;
    }

    @Override
    public List<Tariff> getAll() {
        List<Tariff> result = null;
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("SELECT e FROM Tariff e");
        result = query.getResultList();

        return result;
    }

    @Override
    public void save(Tariff tariff) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tariff);

    }

    /*@Override
    public void update(Tariff tariff, String[] params) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            if(tariff != null){
                tariff.setTariff_name(params[0]);
                tariff.setPrice(Integer.parseInt(params[1]));
                tariff.setAvailableToConnectOrNotStatus(Boolean.parseBoolean(params[2]));

                session.beginTransaction();
                session.update(tariff);
                session.getTransaction().commit();
            }else{
                System.out.println("Tariff doesn't exist with provideded Id..");
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }*/

    @Override
    public void delete(int tariffId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Tariff " +
                "where id =:tariffId");
        query.setParameter("tariffId", tariffId);
        query.executeUpdate();

    }

}
