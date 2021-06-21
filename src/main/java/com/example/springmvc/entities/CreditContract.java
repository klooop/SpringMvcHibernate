package com.example.springmvc.entities;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
@Data
@Entity
@Table (name= "credit_decision")
public class CreditContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private  Long contractId;
    @Column(name="credit_status")
    private String creditStatus;
    @Column(name="time")
    private LocalDate time;
    @Column(name="credit_sum")
    private int creditSum;

    @OneToOne(mappedBy = "creditContract")
    private Credit credit;
}
