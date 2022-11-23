package com.CnS.domain.admin.dto;

import com.sun.istack.NotNull;
import lombok.Getter;

@Getter
public class CourseModifyRequestDto {

    private String name;

    @NotNull
    private Integer courseNumber;

    @NotNull
    private Integer classNumber;

    private Integer capacity;


}
