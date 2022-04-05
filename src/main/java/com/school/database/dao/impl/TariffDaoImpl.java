package com.school.database.dao.impl;

import com.school.database.dao.contracts.TariffDao;
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

    /**
     * Described at {@link TariffDao}
     * @param id id of tariff which we are looking for
     * @return tariff with required id
     */
    @Override
    public Tariff get(int id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Tariff.class, id);

    }

    /**
     * Described at {@link TariffDao}
     * @param name name of tariff which we are looking for
     * @return tariff with required name
     */
    @Override
    public Tariff getByName(String name) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Tariff where tariffName=:name");
        query.setParameter("name", name);

        return  (Tariff) query.getSingleResult();

    }

    /**
     * Described at {@link TariffDao}
     * @return list of tariffs
     */
    @Override
    public List<Tariff> getAll() {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("SELECT e FROM Tariff e");
        return query.getResultList();

    }

    /**
     * Described at {@link TariffDao}
     * @return list of available to connect tariffs
     */
    @Override
    public List<Tariff> getAllAvailable() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Tariff where availableToConnectOrNotStatus=:status");
        query.setParameter("status", true);

        return query.getResultList();
    }

    /**
     * Described at {@link TariffDao}
     * @param pageSize number of records on one page
     * @param sortColumn filed which will be used as field to compare tariffs
     * @param pageNumber number of page where tariffs will be shown
     * @return list of tariffs for show on page
     */
    @Override
    public List<Tariff> getPageOfTariffs(int pageSize, String sortColumn, int pageNumber) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Tariff tariff order by tariff." + sortColumn + " desc");

        query.setFirstResult(pageSize * (pageNumber - 1));
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    /**
     * Described at {@link TariffDao}
     * @param sizeOfPage number of record on one page
     * @return number of pages
     */
    @Override
    public int getNumberOfPages(int sizeOfPage) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select count(*) from Tariff ");

        Integer numberOfRecords = Integer.parseInt(query.getSingleResult().toString());

        return (int) Math.ceil((double) numberOfRecords / sizeOfPage);
    }

    /**
     * Described at {@link TariffDao}
     * @param tariff tariff which need to be saved or updated
     */
    @Override
    public void save(Tariff tariff) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tariff);

    }

    /**
     * Described at {@link TariffDao}
     * @param id id of tariff which should be deleted
     */
    @Override
    public void delete(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Tariff " +
                "where id =:tariffId");
        query.setParameter("tariffId", id);
        query.executeUpdate();

    }

}
