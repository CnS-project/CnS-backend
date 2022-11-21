package com.CnS.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "course")
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private long courseId;

    @Column(name = "target_grade")
    private Integer targetGrade;

    private Integer credit;

    private String name;

    @Column(name = "course_number")
    private Integer courseNumber;

    @Column(name = "class_number")
    private Integer classNumber;

    private String professor;

    private Integer capacity;

    public Course() { }
}
