package com.school.database.dao;

import com.school.database.entity.Tariff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class TariffDaoImpl implements Dao<Tariff> {

    private final SessionFactory sessionFactory;

    TariffDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Tariff get(int id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Tariff.class, id);

    }

    @Override
    public Tariff getByName(String name) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Tariff where tariffName=:name");
        query.setParameter("name", name);

        return  (Tariff) query.getSingleResult();

    }

    @Override
    public List<Tariff> getAll() {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("SELECT e FROM Tariff e");
        return query.getResultList();

    }

    @Override
    public void save(Tariff tariff) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tariff);

    }

    @Override
    public void delete(int tariffId) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Tariff " +
                "where id =:tariffId");
        query.setParameter("tariffId", tariffId);
        query.executeUpdate();

    }

}
