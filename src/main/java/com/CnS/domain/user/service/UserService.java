package com.CnS.domain.user.service;

import static com.CnS.global.constant.SessionConstants.SESSION_ID;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final RegisterCourseRepository registerCourseRepository;

    public void login(LoginDto dto, HttpServletRequest request) {
        Optional<Student> existStudent = userRepository.findById(dto.getStudentId());
        if (existStudent.isPresent()) {
            String password = existStudent.get().getPassword();
            if (dto.getPassword().equals(password)) {
                Student student = existStudent.get();

                HttpSession session = request.getSession();
                session.setAttribute(SESSION_ID, student.getStudentId());
            } else {
                throw new UserException("패스워드가 잘못되었습니다.", ErrorCode.INVALID_USER_PASSWORD);
            }

        } else {
            throw new UserException("아이디가 잘못되었습니다.", ErrorCode.INVALID_USER_ID);
        }
    }

    public void registerCourse(RegisterCourseRequestDto registerCourseRequestDto,
        HttpServletRequest request) {

        // 1. 로그인이 되었는지 체크
        HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_ID) == null) {
            throw new UserException("세션 정보가 없습니다.", ErrorCode.NONE_SESSION_INFORMATION);
        }

        int userId = (int) session.getAttribute(SESSION_ID);

        // 2. 학수 번호 생성
        String courseId = registerCourseRequestDto.getCourseNumber() + "-" +
            registerCourseRequestDto.getClassNumber();

        Optional<Course> existCourse = courseRepository.findById(courseId);

        // 3. 해당 강의가 존재하는지 체크
        if (existCourse.isEmpty()) {
            throw new CourseException(ErrorCode.COURSE_NOT_EXIST);
        }

        RegisterCourseId registerCourseId = RegisterCourseId.builder()
            .courseId(courseId)
            .studentId(userId).build();

        Optional<RegisterCourse> existRegisterCourse = registerCourseRepository.findById(
            registerCourseId);

        // 4. 이미 신청한 강의인지 체크
        if (existRegisterCourse.isPresent()) {
            throw new UserException("이미 신청한 수업입니다.", ErrorCode.DUPLICATE_REGISTER);
        }

        // 5. 수강 신청 정보 저장
        registerCourseRepository.save(
            RegisterCourse.builder().
                registerCourseId(registerCourseId)
                .build()
        );
    }
}
