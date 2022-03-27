package com.school.database.dao.impl;

import com.school.database.dao.contracts.NumberDao;
import com.school.database.entity.Number;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class NumberDaoImpl implements NumberDao {

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
    public Number getByPhoneNumber(String phoneNumber) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Number where phoneNumber=:phoneNumber");
        query.setParameter("phoneNumber", phoneNumber);

        return (Number) query.getSingleResult();

    }

    @Override
    public List<Number> getAll() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Number ",Number.class);

        return query.getResultList();

    }

    @Override
    public List<String> getAllUsed() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select phoneNumber from Number where availableToConnectStatus=:status");
        query.setParameter("status", false);

        return query.getResultList();
    }

    @Override
    public List<String> getAllUnused() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select phoneNumber from Number where availableToConnectStatus=:status");
        query.setParameter("status", true);

        return query.getResultList();
    }

    @Override
    public boolean checkNumberToUnique(Number number) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select count(*) from Number where phoneNumber=:number");
        query.setParameter("number", number.getPhoneNumber());

        Integer result = Integer.parseInt(query.getSingleResult().toString());

        if (result.compareTo(0) == 0)
            return true;

        return false;
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
