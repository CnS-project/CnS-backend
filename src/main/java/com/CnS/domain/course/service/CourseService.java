package com.CnS.domain.course.service;

import com.CnS.domain.admin.dto.CourseDeleteRequestDto;
import com.CnS.domain.admin.dto.CourseInsertRequestDto;
import com.CnS.domain.admin.dto.CourseModifyRequestDto;
import com.CnS.domain.course.entity.Course;
import com.CnS.domain.course.dto.CourseInquiryResponseDto;
import com.CnS.domain.course.repository.CourseRepository;
import com.CnS.global.error.exception.CourseException;
import com.CnS.global.error.exception.ErrorCode;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    @Transactional
    public CourseInquiryResponseDto getCourses() {
        return CourseInquiryResponseDto.builder()
            .courses(courseRepository.findAll())
            .build();
    }
}
