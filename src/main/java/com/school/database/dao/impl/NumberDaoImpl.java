package com.school.database.dao.impl;

import com.school.database.dao.contracts.NumberDao;
import com.school.database.entity.Number;
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

    /**
     * Described at {@link NumberDao}
     * @param id id of phone number which we are looking for
     * @return number with inputted id
     */
    @Override
    public Number get(int id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Number.class, id);

    }

    /**
     * Described at {@link NumberDao}
     * @param phoneNumber value of phone number which we are looking for
     * @return phone number entity
     */
    @Override
    public Number getByPhoneNumber(String phoneNumber) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Number where phoneNumber=:phoneNumber");
        query.setParameter("phoneNumber", phoneNumber);

        return (Number) query.getSingleResult();

    }

    /**
     * Described at {@link NumberDao}
     * @return list of phone numbers
     */
    @Override
    public List<Number> getAll() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Number ",Number.class);

        return query.getResultList();

    }

    /**
     * Described at {@link NumberDao}
     * @return list of phone numbers which already used by clients
     */
    @Override
    public List<String> getAllUsed() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select phoneNumber from Number where availableToConnectStatus=:status");
        query.setParameter("status", false);

        return query.getResultList();
    }

    /**
     * Described at {@link NumberDao}
     * @return list of available to connect phone numbers
     */
    @Override
    public List<String> getAllUnused() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select phoneNumber from Number where availableToConnectStatus=:status");
        query.setParameter("status", true);

        return query.getResultList();
    }

    /**
     * Described at {@link NumberDao}
     * @param number number which is checked to unique status
     * @return true if unique, false otherwise
     */
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

    /**
     * Described at {@link NumberDao}
     * @param number number which need to be saved or updated
     */
    @Override
    public void save(Number number) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(number);

    }

    /**
     * Described at {@link NumberDao}
     * @param id id of phone's number which need to be deleted
     */
    @Override
    public void delete(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Number " +
                "where id =:numberId");
        query.setParameter("numberId", id);
        query.executeUpdate();

    }
}
