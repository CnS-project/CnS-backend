package com.CnS.domain.user.service;

import com.CnS.domain.course.entity.Course;
import com.CnS.domain.course.repository.CourseRepository;
import com.CnS.domain.user.dto.LoginDto;
import com.CnS.domain.user.dto.RegisterCourseRequestDto;
import com.CnS.domain.user.entity.RegisterCourse;
import com.CnS.domain.user.entity.RegisterCourseId;
import com.CnS.domain.user.entity.Student;
import com.CnS.domain.user.repository.RegisterCourseRepository;
import com.CnS.domain.user.repository.UserRepository;
import com.CnS.global.error.exception.CourseException;
import com.CnS.global.error.exception.ErrorCode;
import com.CnS.global.error.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final RegisterCourseRepository registerCourseRepository;
    public List<Course> courseList(Integer id){
        List<String> coursesId = registerCourseRepository.findByStudentId(id);
        List<Course> allByCoursesId = courseRepository.findAllByCoursesId(coursesId);
        return allByCoursesId;

    }
    public Student login(LoginDto dto) {
        Optional<Student> student = userRepository.findById(dto.getStudentId());
        if(student.isPresent()){
            String password = student.get().getPassword();
            if(dto.getPassword().equals(password)){
                return student.get();
            }else{
                throw new IllegalArgumentException();
            }

        }else{
            throw new IllegalArgumentException();
        }
    }

    public void registerCourse(RegisterCourseRequestDto registerCourseRequestDto) {
        // 1. 학수 번호 생성
        String courseId = registerCourseRequestDto.getCourseNumber() + "-" +
            registerCourseRequestDto.getClassNumber();

        Optional<Course> existCourse = courseRepository.findById(courseId);

        // 2. 해당 강의가 존재하는지 체크
        if (existCourse.isEmpty()) {
            throw new CourseException(ErrorCode.COURSE_NOT_EXIST);
        }

        // 세션 구현 시 세션에서 가져오기
        int userId = 201702041;

        RegisterCourseId registerCourseId = RegisterCourseId.builder()
            .courseId(courseId)
            .studentId(userId).build();

        Optional<RegisterCourse> existRegisterCourse = registerCourseRepository.findById(registerCourseId);

        // 3. 이미 신청한 강의인지 체크
        if (existRegisterCourse.isPresent()) {
            throw new UserException("이미 신청한 수업입니다.",ErrorCode.DUPLICATE_REGISTER);
        }

        registerCourseRepository.save(
            RegisterCourse.builder().
                registerCourseId(registerCourseId)
                .build()
        );
    }
}
