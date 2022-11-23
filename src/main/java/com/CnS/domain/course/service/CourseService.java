package com.CnS.domain.course.service;

import com.CnS.domain.course.dto.CourseDeleteRequestDto;
import com.CnS.domain.course.dto.CourseInsertRequestDto;
import com.CnS.domain.course.dto.CourseModifyRequestDto;
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
    @Transactional
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
                .targetGrade(courseInsertRequestDto.getTargetGrade())
                .credit(courseInsertRequestDto.getCredit())
                .name(courseInsertRequestDto.getName())
                .courseNumber(courseInsertRequestDto.getCourseNumber())
                .classNumber(courseInsertRequestDto.getClassNumber())
                .professor(courseInsertRequestDto.getProfessor())
                .capacity(courseInsertRequestDto.getCapacity())
                .applicant(courseInsertRequestDto.getApplicant())
                .major(courseInsertRequestDto.getMajor())
                .build()
        );
    }
    @Transactional
    public void modifyCourse(CourseModifyRequestDto courseModifyRequestDto) {
        // 학수 번호 = 과목번호-분반
        String courseId = courseModifyRequestDto.getCourseNumber() + "-" +
            courseModifyRequestDto.getClassNumber();

        Optional<Course> existCourse =
            courseRepository.findById(courseId);

        if (existCourse.isEmpty()) {
            throw new CourseException("강의가 존재하지 않습니다", ErrorCode.COURSE_NOT_EXIST);
        }

        courseRepository.save(
            Course.builder()
                .courseId(courseId)
                .targetGrade(courseModifyRequestDto.getTargetGrade())
                .credit(courseModifyRequestDto.getCredit())
                .name(courseModifyRequestDto.getName())
                .courseNumber(courseModifyRequestDto.getCourseNumber())
                .classNumber(courseModifyRequestDto.getClassNumber())
                .professor(courseModifyRequestDto.getProfessor())
                .capacity(courseModifyRequestDto.getCapacity())
                .applicant(courseModifyRequestDto.getApplicant())
                .major(courseModifyRequestDto.getMajor())
                .build()
        );
    }
    @Transactional
    public void deleteCourse(CourseDeleteRequestDto courseDeleteRequestDto) {
        // 학수 번호 = 과목번호-분반
        String courseId = courseDeleteRequestDto.getCourseNumber() + "-" +
            courseDeleteRequestDto.getClassNumber();

        Optional<Course> existCourse =
            courseRepository.findById(courseId);

        if (existCourse.isEmpty()) {
            throw new CourseException("강의가 존재하지 않습니다", ErrorCode.COURSE_NOT_EXIST);
        }

        courseRepository.deleteById(courseId);
    }
}
