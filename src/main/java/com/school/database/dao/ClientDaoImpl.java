package com.school.database.dao;

import com.school.database.entity.Client;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.GregorianCalendar;
import java.util.List;

@Repository
public class ClientDaoImpl implements Dao<Client> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Client get(int id) {
        Client client = null;

        Session session = sessionFactory.getCurrentSession();
        client = session.get(Client.class, id);

        return client;
    }

    @Override
    public List<Client> getAll() {
        List<Client> result = null;

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Client ", Client.class);
        result = query.getResultList();

        return result;
    }

    @Override
    public void save(Client client) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(client);

    }

    /*@Override
    public void update(Client client, String[] params) {
        try (Session session = sessionFactory.openSession()) {

            if(client != null){
                client.setFirst_name(params[0]);
                client.setSurname(params[1]);
                String[] date = params[2].split("-");
                client.setDate_of_birth(new java.sql.Date(new GregorianCalendar
                        (Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])).getTime().getTime()));
                client.setPassport_number(params[3]);
                client.setAddress(params[4]);
                client.setPhone_number(params[5]);
                client.setEmail_address(params[6]);
                client.setPassword_log_in(params[7]);
                client.setClientNumberBlockStatus(Boolean.parseBoolean(params[8]));
                client.setUserRole(params[9]);
                client.setRoleOfUserWhoBlockedNumber(params[10]);
                session.beginTransaction();
                session.update(client);
                session.getTransaction().commit();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }*/

   @Override
    public void delete(int clientId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Client " +
                "where id =:clientId");
        query.setParameter("clientId", clientId);
        query.executeUpdate();

    }

}
