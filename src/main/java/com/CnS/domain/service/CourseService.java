package com.CnS.domain.service;

import com.CnS.domain.dto.course.CourseInquiryResponseDto;
import com.CnS.domain.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseInquiryResponseDto getCourses() {
        return CourseInquiryResponseDto.builder()
            .courses(courseRepository.findAll())
            .build();
    }


}
