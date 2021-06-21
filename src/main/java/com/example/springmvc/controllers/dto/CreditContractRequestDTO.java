package com.example.springmvc.controllers.dto;

import com.example.springmvc.entities.Credit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class CreditContractRequestDTO {
    private String surname;
    private String name;
    private String middle_name;
    private int passport_series;
    private int passport_number;
    private Boolean is_married;
    private String address;
    private Long phone;
    private int work_period_months;
    private String work_position;
    private String work_organization;
    private int sum;
    private boolean status;
    private int days;
    private int approvedSum;
    private LocalDate time;
    private String confirmStatus;
    private Long creditContractId;

    public CreditContractRequestDTO(Credit credit,Long contractId) {
        this.surname=credit.getSurname();
        this.name = credit.getName();
        this.middle_name=credit.getMiddle_name();
        this.passport_series= credit.getPassport_series();
        this.passport_number= credit.getPassport_number();
        this.is_married=credit.getIs_married();
        this.address= credit.getAddress();
        this.phone=credit.getPhone();
        this.work_period_months=credit.getWork_period_months();
        this.work_position=credit.getWork_position();
        this.work_organization=credit.getWork_organization();
        this.sum = credit.getSum();
        this.status=true;
        this.days=credit.getDays();
        this.approvedSum=credit.getApprovedSum();
        this.time=LocalDate.now();
        this.confirmStatus="Еще не подписано";
        this.creditContractId=contractId;
    }
}
