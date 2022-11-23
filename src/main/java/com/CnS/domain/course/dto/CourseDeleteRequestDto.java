package com.CnS.domain.course.dto;

import com.sun.istack.NotNull;
import lombok.Getter;

@Getter
public class CourseDeleteRequestDto {
    @NotNull
    private Integer courseNumber;

    @NotNull
    private Integer classNumber;

}
