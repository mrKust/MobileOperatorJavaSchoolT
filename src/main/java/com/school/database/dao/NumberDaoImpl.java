package com.school.database.dao;

import com.school.database.entity.Client;
import com.school.database.entity.Number;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class NumberDaoImpl implements Dao<Number> {

    private final SessionFactory sessionFactory;

    NumberDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Number get(int id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Number.class, id);

    }

    @Override
    public Number getByName(String num) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Number where phoneNumber=:phoneNumber");
        query.setParameter("phoneNumber", num);

        return (Number) query.getSingleResult();

    }

    @Override
    public List<Number> getAll() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Number ",Number.class);

        return query.getResultList();

    }

    @Override
    public void save(Number number) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(number);

    }

    @Override
    public void delete(int numberId) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Number " +
                "where id =:numberId");
        query.setParameter("numberId", numberId);
        query.executeUpdate();

    }
}
