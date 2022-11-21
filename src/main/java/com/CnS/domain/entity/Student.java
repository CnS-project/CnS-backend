package com.CnS.domain.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter
@Entity
@Table(name = "sns")
public class Student {
    @Id
    private int id;

    private String name;

    private String password;

    private String email;

    private int grade;

    private int credits;



}
