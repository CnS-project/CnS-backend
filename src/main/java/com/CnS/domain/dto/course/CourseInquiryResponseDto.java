package com.CnS.domain.dto.course;

import com.CnS.domain.entity.Course;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CourseInquiryResponseDto {

    private List<Course> courses;
}
