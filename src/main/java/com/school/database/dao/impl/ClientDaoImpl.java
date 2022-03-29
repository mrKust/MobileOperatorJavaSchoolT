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

    @Override
    public Client get(int id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Client.class, id);

    }

    @Override
    public Client getByEmail(String email) {

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
    public boolean checkUserEmailToUnique(Client client) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select count(*) from Client where emailAddress=:email");
        query.setParameter("email", client.getEmailAddress());

        Integer result = Integer.parseInt(query.getSingleResult().toString());

        if (result.compareTo(0) == 0)
            return true;

        return false;
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
