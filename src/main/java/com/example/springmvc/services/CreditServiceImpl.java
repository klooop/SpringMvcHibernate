package com.example.springmvc.services;

import com.example.springmvc.dao.CreditDAO;
import com.example.springmvc.entities.Credit;
import com.example.springmvc.entities.CreditContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class CreditServiceImpl implements CreditService{

    private final CreditDAO creditDAO;
    @Autowired
    public CreditServiceImpl(CreditDAO creditDAO) {
        this.creditDAO = creditDAO;
    }

    @Override
    public Credit getCreditByContractId(Long contractId) {
        return creditDAO.getCreditByContractId(contractId);
    }

    @Override
    public CreditContract getCreditContractById(Long contractId) {
        return creditDAO.getCreditContractById(contractId);
    }

    @Override
    public List<Credit> getListOfCredits() {
        return creditDAO.getListOfCredits();
    }

    @Override
    public List<CreditContract> getListOfCreditContracts() {
        return creditDAO.getListOfCreditContracts();
    }

    @Override
    public void makeRequestOnCredit(Credit credit) {
        Random random = new Random();
        boolean status=random.nextBoolean();
        credit.setStatus(status);
        credit.setDays(randomValue(30, 365));
        if (status){
            int approvedSum = randomValue(1, credit.getSum());
            credit.setApprovedSum(approvedSum);
            CreditContract creditContract = new CreditContract();
            creditContract.setCreditSum(approvedSum);
            credit.setCreditContract(creditContract);
            creditContract.setCreditStatus("Еще не подписано");
            creditDAO.save(creditContract);
        }
        else credit.setApprovedSum(0);
        creditDAO.save(credit);
    }
        public void signContract(CreditContract creditContract, String confirmStatus, LocalDate time){
        if (confirmStatus.equals("on")) {
            creditContract.setCreditStatus("Подписано");
            creditContract.setTime(time);
        }
        else creditContract.setCreditStatus("Еще не подписано");
        creditDAO.save(creditContract);
    }

    @Override
    public List<Credit> findCreditsByStatusIsTrue() {
        return creditDAO.findCreditsByStatusIsTrue();
    }

    @Override
    public List<CreditContract> findCreditContractByCreditStatusIsTrue() {
        return creditDAO.findCreditContractByCreditStatusIsTrue();
    }

    @Override
    public List<Credit> creditsWithFiltering(Long phone,String name, String surname, String middle_name,
                                             Integer passport_series, Integer passport_number) {
        return creditDAO.creditsWithFiltering( phone, name,surname, middle_name, passport_series, passport_number);
    }

    private static int randomValue(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
