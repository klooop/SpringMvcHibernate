package com.example.springmvc.dao;

import com.example.springmvc.entities.Credit;
import com.example.springmvc.entities.CreditContract;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@EnableTransactionManagement
public class CreditDAOImpl  implements CreditDAO{
    private final SessionFactory sessionFactory;
    @Autowired
    public CreditDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public List<Credit> getListOfCredits() {
        Session session = sessionFactory.openSession();

        List<Credit> creditList = session.createQuery("from Credit ", Credit.class)
                .getResultList();
        session.close();


        return creditList;
    }
    @Transactional
    @Override
    public List<CreditContract> getListOfCreditContracts() {
        Session session = sessionFactory.openSession();

        List<CreditContract> creditContractList = session.createQuery("from CreditContract order by contractId ", CreditContract.class)
                .getResultList();
        session.close();


        return creditContractList;
    }
    @Transactional
    @Override
    public void save(Credit credit) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(credit);
        session.getTransaction().commit();
        session.close();
    }
    @Transactional
    @Override
    public void save(CreditContract creditContract) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(creditContract);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Credit getCreditByContractId(Long contractId) {
        Session session = sessionFactory.openSession();
        CreditContract creditContract = session.get(CreditContract.class, contractId);
        session.close();
        return creditContract.getCredit();
    }

    @Override
    public CreditContract getCreditContractById(Long contractId) {
        Session session = sessionFactory.openSession();
        CreditContract creditContract = session.get(CreditContract.class, contractId);
        session.close();
        return creditContract;
    }

    @Override
    public List<Credit> findCreditsByStatusIsTrue() {
        Session session = sessionFactory.openSession();

        List<Credit> credits = session.createQuery("from Credit where status=true ", Credit.class)
                .getResultList();
        session.close();
        return credits;
    }

    @Override
    public List<CreditContract> findCreditContractByCreditStatusIsTrue() {
        Session session = sessionFactory.openSession();

        List<CreditContract> credits = session.createQuery("from CreditContract where creditStatus like 'Подписано' ",
                CreditContract.class).getResultList();
        session.close();
        return credits;
    }

    @Override
    public List<Credit> creditsWithFiltering(Long phone,String name, String surname, String middle_name,
                                             Integer passport_series, Integer passport_number) {
        Session session = sessionFactory.openSession();
        if (phone!=null){
            Filter filter= session.enableFilter("phoneCheck");
            filter.setParameter("phone1", phone);
        }
        if (name!=null&&!name.equals("")){
            Filter filter= session.enableFilter("nameCheck");
            filter.setParameter("name1", name);
        }
        if (surname!=null&&!surname.equals("")){
            System.out.println(surname);
            Filter filter= session.enableFilter("surnameCheck");
            filter.setParameter("surname1", surname);
        }
        if (middle_name!=null&&!middle_name.equals("")){
            Filter filter= session.enableFilter("middle_nameCheck");
            filter.setParameter("middle_name1", middle_name);
        }
        if (passport_series!=null){
            Filter filter= session.enableFilter("passport_seriesCheck");
            filter.setParameter("passport_series1", passport_series);
        }
        if (passport_number!=null){
            Filter filter= session.enableFilter("passport_numberCheck");
            filter.setParameter("passport_number1", passport_number);
        }

        List<Credit> creditList = session.createQuery("from Credit ", Credit.class)
                .getResultList();

        session.close();

        return creditList;
    }
}
