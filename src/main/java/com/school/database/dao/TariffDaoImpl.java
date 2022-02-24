package com.school.database.dao;

import com.school.database.dao.Dao;
import com.school.database.entity.Tariff;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
public class TariffDaoImpl implements Dao<Tariff> {

    @Autowired
    private SessionFactory sessionFactory;

    /*@Override
    public Tariff get(int id) {
        Tariff tariff = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            tariff = session.get(Tariff.class, id);
            if (tariff != null)
                return tariff;
            else System.out.println("Tariff with this id not found");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    @Override
    @Transactional
    public List<Tariff> getAll() {
        List<Tariff> result = null;
        try (Session session = sessionFactory.openSession()) {

            Query query = session.createQuery("SELECT e FROM Tariff e");
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
    public void save(Tariff tariff) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Integer id =(Integer)session.save(tariff);
            System.out.println("Tariff is created  with Id::"+id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
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

    }

    @Override
    public void delete(int tariffId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Tariff tariff = session.get(Tariff.class, tariffId);
            if(tariff != null){
                session.beginTransaction();

                session.delete(tariff);
                session.getTransaction().commit();
            }else{
                System.out.println("Employee doesn't exist with provideded Id..");
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }*/

}
