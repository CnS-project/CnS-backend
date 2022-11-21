package com.CnS.domain.service;

import com.CnS.domain.dto.course.CourseInquiryResponseDto;
import com.CnS.domain.dto.course.CourseInsertRequestDto;
import com.CnS.domain.entity.Course;
import com.CnS.domain.repository.CourseRepository;
import java.util.Optional;
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

    public void insertCourse(CourseInsertRequestDto courseInsertRequestDto) {

        // 학수 번호 = 과목번호-분반
        String courseId = courseInsertRequestDto.getCourseNumber() + "-" +
            courseInsertRequestDto.getClassNumber();

        Optional<Course> course =
            courseRepository.findById(courseId);

        if (course.isPresent()) {
            throw new IllegalArgumentException("중복된 과목 존재");
        }

        courseRepository.save(
            Course.builder()
                .courseId(courseId)
                .courseNumber(courseInsertRequestDto.getCourseNumber())
                .name(courseInsertRequestDto.getName())
                .classNumber(courseInsertRequestDto.getClassNumber())
                .professor(courseInsertRequestDto.getProfessor())
                .targetGrade(courseInsertRequestDto.getTargetGrade())
                .capacity(courseInsertRequestDto.getCapacity())
                .credit(courseInsertRequestDto.getCredit())
                .build()
        );
    }

}
