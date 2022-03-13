package com.school.database.dao;

import com.school.database.entity.Client;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.GregorianCalendar;
import java.util.List;

@Repository
public class ClientDaoImpl implements Dao<Client> {

    private final SessionFactory sessionFactory;

    ClientDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Client get(int id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Client.class, id);

    }

    @Override
    public Client getByName(String email) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Client where emailAddress=:email");
        query.setParameter("email", email);

        return  (Client) query.getSingleResult();

    }

    @Override
    public List<Client> getAll() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Client ", Client.class);
        return query.getResultList();

    }

    @Override
    public void save(Client client) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(client);

    }

   @Override
    public void delete(int clientId) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Client " +
                "where id =:clientId");
        query.setParameter("clientId", clientId);
        query.executeUpdate();

    }

}
