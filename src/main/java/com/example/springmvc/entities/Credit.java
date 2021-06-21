package com.example.springmvc.entities;

import com.example.springmvc.controllers.dto.CreditResponseDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;


@FilterDefs({
        @FilterDef(name="phoneCheck", parameters={
                @ParamDef( name="phone1", type="long")
        }),
        @FilterDef(name="nameCheck", parameters={
                @ParamDef( name="name1", type="string" )
        }),
        @FilterDef(name="surnameCheck", parameters={
                @ParamDef( name="surname1", type="string")
        }),
        @FilterDef(name="middle_nameCheck", parameters={
                @ParamDef( name="middle_name1", type="string")
        }),
        @FilterDef(name="passport_seriesCheck", parameters={
                @ParamDef( name="passport_series1", type="integer")
        }),
        @FilterDef(name="passport_numberCheck", parameters={
                @ParamDef( name="passport_number1", type="integer")
        })
})

@Filters( {
        @Filter(name="phoneCheck", condition="phone=:phone1 "),
        @Filter(name="nameCheck", condition="name=:name1"),
        @Filter(name="surnameCheck", condition="surname=:surname1"),
        @Filter(name="middle_nameCheck", condition="middle_name=:middle_name1"),
        @Filter(name="passport_seriesCheck", condition="passport_series=:passport_series1"),
        @Filter(name="passport_numberCheck", condition="passport_number=:passport_number1")
} )
@Data
@NoArgsConstructor
@Getter
@Entity
@Table(name = "credits")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "middle_name")
    private String middle_name;

    @Column(name = "passport_series")
    private int passport_series;

    @Column(name = "passport_number")
    private int passport_number;

    @Column(name = "is_married")
    private Boolean is_married;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "work_period_months")
    private int work_period_months;

    @Column(name = "work_position")
    private String work_position;

    @Column(name = "work_organization")
    private String work_organization;

    @Column(name = "sum")
    private int sum;

    @Column(name = "status")
    private boolean status;

    @Column(name = "answer_days")
    private int days;

    @Column(name = "approved_sum")
    private int approvedSum;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_decision_id", referencedColumnName = "contract_id")
    private CreditContract creditContract;

    public Credit(CreditResponseDTO cr) {
        this.surname = cr.getSurname();
        this.name = cr.getName();
        this.middle_name = cr.getMiddle_name();
        this.passport_series = cr.getPassport_series();
        this.passport_number = cr.getPassport_number();
        if (cr.getIs_married().equals("yes")) this.is_married = true;
        else this.is_married=false;
        this.address = cr.getAddress();
        this.phone = cr.getPhone();
        this.work_period_months = cr.getWork_period_months();
        this.work_position = cr.getWork_position();
        this.work_organization = cr.getWork_organization();
        this.sum = cr.getSum();
    }
}
