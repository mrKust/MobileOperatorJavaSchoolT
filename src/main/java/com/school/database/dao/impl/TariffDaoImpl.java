package com.school.database.dao.impl;

import com.school.database.dao.contracts.TariffDao;
import com.school.database.entity.Options;
import com.school.database.entity.Tariff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class TariffDaoImpl implements TariffDao {

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
    public List<Tariff> getAllAvailable() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Tariff where availableToConnectOrNotStatus=:status");
        query.setParameter("status", true);

        return query.getResultList();
    }

    @Override
    public List<Tariff> getPageOfTariffs(int pageSize, String sortColumn, int pageNumber) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Tariff tariff order by tariff." + sortColumn + " desc");

        query.setFirstResult(pageSize * (pageNumber - 1));
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public int getNumberOfPages(int sizeOfPage) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select count(*) from Tariff ");

        Integer numberOfRecords = Integer.parseInt(query.getSingleResult().toString());

        return (int) Math.ceil((double) numberOfRecords / sizeOfPage);
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
