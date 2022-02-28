package com.school.database.dao;

import com.school.database.entity.Contract;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ContractDaoImpl implements Dao<Contract> {


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Contract get(int id) {
        Contract contract = null;

        Session session = sessionFactory.getCurrentSession();
        contract = session.get(Contract.class, id);

        return contract;
    }

    @Override
    public List<Contract> getAll() {

        List<Contract> result = null;
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("SELECT e FROM Contract e");
        result = query.getResultList();

        return result;
    }

    @Override
    public void save(Contract contract) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(contract);

    }

    /*@Override
    public void update(Contract contract, String[] params) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            if(contract != null){

                contract.setPhoneNumber(Objects.requireNonNull(params[0], "Number cannot be null"));
                session.beginTransaction();
                session.update(contract);
                session.getTransaction().commit();
            }else{
                System.out.println("Employee doesn't exist with provideded Id..");
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void delete(int contractId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Contract " +
                "where id =:contractId");
        query.setParameter("contractId", contractId);
        query.executeUpdate();

    }

}
