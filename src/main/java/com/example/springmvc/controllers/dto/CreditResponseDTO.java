package com.example.springmvc.controllers.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Getter
@Setter
@NoArgsConstructor
public class CreditResponseDTO {
    private String surname;
    private String name;
    private String middle_name;
    private int passport_series;
    private int passport_number;
    private String is_married;
    private String address;
    private Long phone;
    private int work_period_months;
    private String work_position;
    private String work_organization;
    private int sum;
}
