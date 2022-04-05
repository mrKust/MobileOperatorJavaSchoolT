package com.school.database.dao.impl;

import com.school.database.dao.contracts.OptionTypeDao;
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

    /**
     * Described at {@link OptionTypeDao}
     * @param id id of option type which we are looking for
     * @return option type with required id
     */
    @Override
    public OptionType get(int id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(OptionType.class, id);

    }

    /**
     * Described at {@link OptionTypeDao}
     * @return list of option types
     */
    @Override
    public List<OptionType> getAll() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from OptionType ", OptionType.class);

        return query.getResultList();

    }

    /**
     * Described at {@link OptionTypeDao}
     * @param optionType option type which need to be saved or updated
     */
    @Override
    public void save(OptionType optionType) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(optionType);

    }

    /**
     * Described at {@link OptionTypeDao}
     * @param optionType option type which checked to unique status
     * @return true if unique, false otherwise
     */
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

    /**
     * Described at {@link OptionTypeDao}
     * @param id id of option type which need to be deleted
     */
    @Override
    public void delete(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from OptionType " +
                "where id =:id");
        query.setParameter("id", id);
        query.executeUpdate();

    }
}
