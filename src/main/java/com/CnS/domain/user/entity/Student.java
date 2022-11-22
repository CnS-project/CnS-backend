package com.CnS.domain.user.entity;


import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "student_id")
    private int studentId;

    private String email;

    private String password;

    private String name;

    private int grade;

    private int credits;

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
