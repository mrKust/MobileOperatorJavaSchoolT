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
    public List<Contract> getAllContractsOfClient(String clientEmail) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Contract where contractClient.emailAddress=:email");
        query.setParameter("email", clientEmail);

        return query.getResultList();
    }

    @Override
    public Contract getByPhoneNumber(String phoneNumber) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Contract where phoneNumber=:phoneNumber");
        query.setParameter("phoneNumber", phoneNumber);

        return  (Contract) query.getSingleResult();
    }

    @Override
    public List<Contract> getAll() {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("SELECT e FROM Contract e");
        return query.getResultList();

    }

    @Override
    public List<Contract> getPageOfContracts(int pageSize, String sortColumn, int pageNumber) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Contract contract order by contract." + sortColumn + " desc");

        query.setFirstResult(pageSize * (pageNumber - 1));
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public int getNumberOfPages(int sizeOfPage) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select count(*) from Contract ");

        Integer numberOfRecords = Integer.parseInt(query.getSingleResult().toString());

        return (int) Math.ceil((double) numberOfRecords / sizeOfPage);
    }

    @Override
    public List<Contract> getPageOfClientContracts(int pageSize, String sortColumn, int pageNumber,
                                                   String email) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Contract contract where contractClient.emailAddress=:email order by contract." + sortColumn + " desc");

        query.setParameter("email", email);
        query.setFirstResult(pageSize * (pageNumber - 1));
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public int getNumberOfClientContractPages(int sizeOfPage,
                                              String email) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select count(*) from Contract contract where contractClient.emailAddress=:email");

        query.setParameter("email", email);
        Integer numberOfRecords = Integer.parseInt(query.getSingleResult().toString());

        return (int) Math.ceil((double) numberOfRecords / sizeOfPage);
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
