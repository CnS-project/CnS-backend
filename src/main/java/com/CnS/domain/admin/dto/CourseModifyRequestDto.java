package com.CnS.domain.admin.dto;

import com.sun.istack.NotNull;
import lombok.Getter;

@Getter
public class CourseModifyRequestDto {

    private Integer targetGrade;

    private Integer credit;

    private String name;

    @NotNull
    private Integer courseNumber;

    @NotNull
    private Integer classNumber;

    private String professor;

    private Integer capacity;

    private Integer applicant;

    private String major;

}
