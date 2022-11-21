package com.CnS.domain.course.dto;

import com.CnS.domain.course.entity.Course;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CourseInquiryResponseDto {

    private List<Course> courses;
}
