package com.school.database.dao.impl;

import com.school.database.dao.contracts.ContractDao;
import com.school.database.entity.Contract;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ContractDaoImpl implements ContractDao {


    private final SessionFactory sessionFactory;

    ContractDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Contract get(int id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Contract.class, id);

    }

    @Override
    public List<Contract> getAll() {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("SELECT e FROM Contract e");
        return query.getResultList();

    }

    @Override
    public void save(Contract contract) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(contract);

    }

    @Override
    public void delete(int contractId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Contract " +
                "where id =:contractId");
        query.setParameter("contractId", contractId);
        query.executeUpdate();

    }

}
