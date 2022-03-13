package com.school.database.dao;

import com.school.database.entity.Options;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OptionsDaoImpl implements Dao<Options> {

    private SessionFactory sessionFactory;

    OptionsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Options get(int id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Options.class, id);

    }

    @Override
    public Options getByName(String name) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Options where optionsName=:name");
        query.setParameter("name", name);

        return (Options) query.getSingleResult();

    }

    @Override
    public List<Options> getAll() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Options ", Options.class);
        return query.getResultList();

    }

    @Override
    public void save(Options options) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(options);

    }

    @Override
    public void delete(int optionsId) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Options " +
                "where id =:optionId");
        query.setParameter("optionId", optionsId);
        query.executeUpdate();

    }

}
