package com.school.database.dao;

import com.school.database.entity.Contract;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ContractDaoImpl implements Dao<Contract> {


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Contract get(int id) {
        Contract contract = null;

        try (Session session = sessionFactory.openSession()){

            contract = session.get(Contract.class, id);

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return contract;
    }

    @Override
    public List<Contract> getAll() {
        List<Contract> result = null;
        try (Session session = sessionFactory.openSession()) {

            Query query = session.createQuery("SELECT e FROM Contract e");
            result = query.getResultList();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void save(Contract contract) {
        try (Session session = sessionFactory.openSession()) {

            session.saveOrUpdate(contract);

        } catch (HibernateException e) {
            e.printStackTrace();
        }
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
    }

    @Override
    public void delete(int contractId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Contract contract = session.get(Contract.class, contractId);
            if(contract != null){
                session.beginTransaction();

                session.delete(contract);
                session.getTransaction().commit();
            }else{
                System.out.println("Contract doesn't exist with provideded Id..");
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }*/

}
