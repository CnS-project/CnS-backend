package com.CnS.domain.course.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "course")
@Builder
@AllArgsConstructor
public class Course {

    @Id
    @Column(name = "course_id")
    private String courseId;

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

    private Integer applicant;

    private String major;

    public void setApplicant(Integer applicant) {
        this.applicant = applicant;
    }

    public Course() { }
}
