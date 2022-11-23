package com.CnS.domain.course.dto;

import com.sun.istack.NotNull;
import lombok.Getter;

@Getter
public class CourseInsertRequestDto {

    @NotNull
    private Integer targetGrade;

    @NotNull
    private Integer credit;

    @NotNull
    private String name;

    @NotNull
    private Integer courseNumber;

    @NotNull
    private Integer classNumber;

    @NotNull
    private String professor;

    @NotNull
    private Integer capacity;

    @NotNull
    private Integer applicant;

    @NotNull
    private String major;
}
