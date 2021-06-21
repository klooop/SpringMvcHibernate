package com.example.springmvc.dao;

import com.example.springmvc.entities.Credit;
import com.example.springmvc.entities.CreditContract;

import java.util.List;

public interface CreditDAO {
    List<Credit> getListOfCredits();
    List<CreditContract> getListOfCreditContracts();
    void save(Credit credit);
    void save(CreditContract creditContract);
    Credit getCreditByContractId(Long id);
    CreditContract getCreditContractById(Long contractId);
    List<Credit> findCreditsByStatusIsTrue();
    List<CreditContract> findCreditContractByCreditStatusIsTrue();
    List<Credit> creditsWithFiltering(Long phone,String name, String surname, String middle_name,
                                      Integer passport_series, Integer passport_number);



}
