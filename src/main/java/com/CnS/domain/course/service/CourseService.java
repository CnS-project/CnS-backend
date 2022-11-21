package com.CnS.domain.course.service;

import com.CnS.domain.course.dto.CourseInsertRequestDto;
import com.CnS.domain.course.entity.Course;
import com.CnS.domain.course.dto.CourseInquiryResponseDto;
import com.CnS.domain.course.repository.CourseRepository;
import com.CnS.global.error.exception.CourseException;
import com.CnS.global.error.exception.ErrorCode;
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

        Optional<Course> existCourse =
            courseRepository.findById(courseId);

        if (existCourse.isPresent()) {
            throw new CourseException("중복된 과목 존재", ErrorCode.DUPLICATE_COURSE);
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
