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

    /**
     * Described at {@link ContractDao}
     * @param id id of searched contract
     * @return contract with id
     */
    @Override
    public Contract get(int id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Contract.class, id);

    }

    /**
     * Described at {@link ContractDao}
     * @param clientEmail email address of client who contracts we want to get
     * @return list of all contracts of one client
     */
    @Override
    public List<Contract> getAllContractsOfClient(String clientEmail) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Contract where contractClient.emailAddress=:email");
        query.setParameter("email", clientEmail);

        return query.getResultList();
    }

    /**
     * Described at {@link ContractDao}
     * @param phoneNumber phone number which locked for contract
     * @return contract with inputted phone number
     */
    @Override
    public Contract getByPhoneNumber(String phoneNumber) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Contract where phoneNumber=:phoneNumber");
        query.setParameter("phoneNumber", phoneNumber);

        return  (Contract) query.getSingleResult();
    }

    /**
     * Described at {@link ContractDao}
     * @return list of contracts in system
     */
    @Override
    public List<Contract> getAll() {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("SELECT e FROM Contract e");
        return query.getResultList();

    }

    /**
     * Described at {@link ContractDao}
     * @param pageSize number of records on one page
     * @param sortColumn filed which will be used as field to compare contracts
     * @param pageNumber number of page where contracts will be shown
     * @return list of contracts for show on page
     */
    @Override
    public List<Contract> getPageOfContracts(int pageSize, String sortColumn, int pageNumber) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Contract contract order by contract." + sortColumn + " desc");

        query.setFirstResult(pageSize * (pageNumber - 1));
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    /**
     * Described at {@link ContractDao}
     * @param sizeOfPage number of record on one page
     * @return number of pages
     */
    @Override
    public int getNumberOfPages(int sizeOfPage) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select count(*) from Contract ");

        Integer numberOfRecords = Integer.parseInt(query.getSingleResult().toString());

        return (int) Math.ceil((double) numberOfRecords / sizeOfPage);
    }

    /**
     * Described at {@link ContractDao}
     * @param pageSize number of records on one page
     * @param sortColumn filed which will be used as field to compare contracts
     * @param pageNumber number of page where contracts will be shown
     * @param clientEmail email address of client which contract we are looking for
     * @return list of contracts for show on page
     */
    @Override
    public List<Contract> getPageOfClientContracts(int pageSize, String sortColumn, int pageNumber,
                                                   String clientEmail) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Contract contract where contractClient.emailAddress=:email order by contract." + sortColumn + " desc");

        query.setParameter("email", clientEmail);
        query.setFirstResult(pageSize * (pageNumber - 1));
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    /**
     * Described at {@link ContractDao}
     * @param sizeOfPage number of record on one page
     * @param clientEmail email address of client which contract we are looking for
     * @return number of pages
     */
    @Override
    public int getNumberOfClientContractPages(int sizeOfPage,
                                              String clientEmail) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select count(*) from Contract contract where contractClient.emailAddress=:email");

        query.setParameter("email", clientEmail);
        Integer numberOfRecords = Integer.parseInt(query.getSingleResult().toString());

        return (int) Math.ceil((double) numberOfRecords / sizeOfPage);
    }

    /**
     * Described at {@link ContractDao}
     * @param contract contract which need to be saved or updated
     */
    @Override
    public void save(Contract contract) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(contract);

    }

    /**
     * Described at {@link ContractDao}
     * @param id id of contract which should be deleted
     */
    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Contract " +
                "where id =:contractId");
        query.setParameter("contractId", id);
        query.executeUpdate();

    }

}
