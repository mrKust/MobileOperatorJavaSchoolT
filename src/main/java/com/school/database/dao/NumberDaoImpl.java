package com.school.database.dao;

import com.school.database.entity.Client;
import com.school.database.entity.Number;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class NumberDaoImpl implements Dao<Number> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Number get(int id) {
        Number number = null;

        Session session = sessionFactory.getCurrentSession();
        number = session.get(Number.class, id);

        return number;
    }

    @Override
    public Number getByName(String num) {
        Number number = null;

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Number where phoneNumber=:phoneNumber");
        query.setParameter("phoneNumber", num);

        number = (Number) query.getSingleResult();

        return number;
    }

    @Override
    public List<Number> getAll() {
        List<Number> result = null;

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Number ",Number.class);
        result = query.getResultList();

        return result;
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
