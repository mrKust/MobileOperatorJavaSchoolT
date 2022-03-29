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

    @Override
    public Options get(int id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Options.class, id);

    }

    @Override
    public List<Options> getAll() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Options", Options.class);
        return query.getResultList();

    }

    @Override
    public List<Options> getAllAvailable() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Options where availableOptionToConnectOrNot=:status");
        query.setParameter("status", true);

        return query.getResultList();
    }

    @Override
    public List<Options> getOptionsFromChosenList(List<Integer> list) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Options where id in :idList");
        query.setParameter("idList", list);

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
