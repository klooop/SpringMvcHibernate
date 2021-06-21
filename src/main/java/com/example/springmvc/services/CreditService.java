package com.example.springmvc.services;

import com.example.springmvc.entities.Credit;
import com.example.springmvc.entities.CreditContract;

import java.time.LocalDate;
import java.util.List;

public interface CreditService {
     Credit getCreditByContractId(Long contractId);
     CreditContract getCreditContractById(Long contractId);
     List<Credit> getListOfCredits();
     List<CreditContract> getListOfCreditContracts();
     void makeRequestOnCredit(Credit credit);
     void signContract(CreditContract creditContract, String confirmStatus, LocalDate time);
     List<Credit> findCreditsByStatusIsTrue();
     List<CreditContract> findCreditContractByCreditStatusIsTrue();

     List<Credit> creditsWithFiltering(Long phone,String name, String surname, String middle_name,
                                       Integer passport_series, Integer passport_number);




     }
