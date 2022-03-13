package com.school.database.dao;

import com.school.database.entity.OptionType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OptionTypeDaoImpl implements Dao<OptionType>{

    private final SessionFactory sessionFactory;

    OptionTypeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public OptionType get(int id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(OptionType.class, id);

    }

    @Override
    public OptionType getByName(String name) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from OptionType where optionType=:name");
        query.setParameter("name", name);

        return (OptionType) query.getSingleResult();

    }

    @Override
    public List<OptionType> getAll() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from OptionType ", OptionType.class);

        return query.getResultList();

    }

    @Override
    public void save(OptionType optionsType) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(optionsType);

    }

    @Override
    public void delete(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from OptionType " +
                "where id =:id");
        query.setParameter("id", id);
        query.executeUpdate();

    }
}
