package com.school.database.dao.impl;

import com.school.database.dao.contracts.OptionsDao;
import com.school.database.entity.Options;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class OptionsDaoImpl implements OptionsDao {

    private SessionFactory sessionFactory;

    OptionsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     *  Described at {@link OptionsDao}
     * @param id id of option which need to be returned
     * @return option with this id
     */
    @Override
    public Options get(int id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Options.class, id);

    }

    /**
     * Described at {@link OptionsDao}
     * @return list of all options in system
     */
    @Override
    public List<Options> getAll() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Options", Options.class);
        return query.getResultList();

    }

    /**
     * Described at {@link OptionsDao}
     * @return list of available to connect options
     */
    @Override
    public List<Options> getAllAvailable() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Options where availableOptionToConnectOrNot=:status");
        query.setParameter("status", true);

        return query.getResultList();
    }

    /**
     * Described at {@link OptionsDao}
     * @param list list with ids of options which we want to get
     * @return list of options
     */
    @Override
    public List<Options> getOptionsFromChosenList(List<Integer> list) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Options where id in :idList");
        query.setParameter("idList", list);

        return query.getResultList();
    }

    /**
     * Described at {@link OptionsDao}
     * @param pageSize number of records on one page
     * @param sortColumn filed which will be used as field to compare options
     * @param pageNumber number of page where options will be shown
     * @return list of options for show on page
     */
    @Override
    public List<Options> getPageOfOptions(int pageSize, String sortColumn, int pageNumber) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Options options order by options." + sortColumn + " desc");
        query.setFirstResult(pageSize * (pageNumber - 1));
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    /**
     * Described at {@link OptionsDao}
     * @param sizeOfPage number of record on one page
     * @return number of pages
     */
    @Override
    public int getNumberOfPages(int sizeOfPage) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select count(*) from Options");

        Integer numberOfRecords = Integer.parseInt(query.getSingleResult().toString());

        return (int) Math.ceil((double) numberOfRecords / sizeOfPage);
    }

    /**
     * Described at {@link OptionsDao}
     * @param options option which need to be saved or updated
     */
    @Override
    public void save(Options options) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(options);

    }

    /**
     * Described at {@link OptionsDao}
     * @param id option's id which need to be deleted
     */
    @Override
    public void delete(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Options " +
                "where id =:optionId");
        query.setParameter("optionId", id);
        query.executeUpdate();

    }

}
