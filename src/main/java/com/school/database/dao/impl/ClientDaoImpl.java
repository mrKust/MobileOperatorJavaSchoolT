package com.school.database.dao.impl;

import com.school.database.dao.contracts.ClientDao;
import com.school.database.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {

    private final SessionFactory sessionFactory;

    ClientDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Described at {@link ClientDao}
     * @param id id of client which we want to get
     * @return client which we have been looking for
     */
    @Override
    public Client get(int id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Client.class, id);

    }

    /**
     * Described at {@link ClientDao}
     * @param email email address of the client which we want to get
     * @return client which we have been looking for
     */
    @Override
    public Client getByEmail(String email) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Client where emailAddress=:email");
        query.setParameter("email", email);

        return  (Client) query.getSingleResult();

    }

    /**
     * Described at {@link ClientDao}
     * @return list which contains all clients from system
     */
    @Override
    public List<Client> getAll() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Client ", Client.class);
        return query.getResultList();

    }

    /**
     * Described at {@link ClientDao}
     * @param client client email address we want check to unique status
     * @return true if unique, false otherwise
     */
    @Override
    public boolean checkUserEmailToUnique(Client client) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select count(*) from Client where emailAddress=:email");
        query.setParameter("email", client.getEmailAddress());

        Integer result = Integer.parseInt(query.getSingleResult().toString());

        if (result.compareTo(0) == 0)
            return true;

        return false;
    }

    /**
     * Described at {@link ClientDao}
     * @param pageSize number of records on one page
     * @param sortColumn filed which will be used as field to compare clients
     * @param pageNumber number of page where client will be shown
     * @return list of client for show on page
     */
    @Override
    public List<Client> getPageOfClients(int pageSize, String sortColumn, int pageNumber) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Client client order by client." + sortColumn + " desc");

        query.setFirstResult(pageSize * (pageNumber - 1));
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    /**
     * Described at {@link ClientDao}
     * @param sizeOfPage number of record on one page
     * @return number of pages
     */
    @Override
    public int getNumberOfPages(int sizeOfPage) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select count(*) from Client ");

        Integer numberOfRecords = Integer.parseInt(query.getSingleResult().toString());

        return (int) Math.ceil((double) numberOfRecords / sizeOfPage);
    }

    /**
     * Described at {@link ClientDao}
     * @param client client which need to be saved or to be updated
     */
    @Override
    public void save(Client client) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(client);

    }

    /**
     * Described at {@link ClientDao}
     * @param id id of client which should be deleted
     */
   @Override
    public void delete(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Client " +
                "where id =:clientId");
        query.setParameter("clientId", id);
        query.executeUpdate();

    }

}
