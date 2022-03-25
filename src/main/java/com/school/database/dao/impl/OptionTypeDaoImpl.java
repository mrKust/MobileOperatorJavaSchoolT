package com.school.database.dao.impl;

import com.school.database.dao.contracts.OptionTypeDao;
import com.school.database.entity.Number;
import com.school.database.entity.OptionType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class OptionTypeDaoImpl implements OptionTypeDao {

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
    public boolean checkOptionTypeToUnique(OptionType optionType) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select count(*) from OptionType where optionType=:name");
        query.setParameter("name", optionType.getOptionType());

        Integer result = Integer.parseInt(query.getSingleResult().toString());

        if (result.compareTo(0) == 0)
            return true;

        return false;
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
