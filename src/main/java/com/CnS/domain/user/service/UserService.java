package com.CnS.domain.user.service;

import static com.CnS.global.constant.SessionConstants.SESSION_ID;

import com.CnS.domain.course.entity.Course;
import com.CnS.domain.course.repository.CourseRepository;
import com.CnS.domain.user.dto.LoginDto;
import com.CnS.domain.user.dto.RegisterCourseRequestDto;
import com.CnS.domain.user.dto.SearchParam;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final RegisterCourseRepository registerCourseRepository;

    @Transactional
    public List<Course> courseList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_ID) == null) {
            throw new UserException("세션 정보가 없습니다.", ErrorCode.NONE_SESSION_INFORMATION);
        }

        int id = (int) session.getAttribute(SESSION_ID);

        List<String> coursesId = registerCourseRepository.findByStudentId(id);
        if (!coursesId.isEmpty()) {
            List<Course> allByCoursesId = courseRepository.findAllByCoursesId(coursesId);

            return allByCoursesId;

        }

        return Collections.emptyList();
    }

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

    @Transactional
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
        Optional<Student> existStudent = userRepository.findById(userId);
        /*
            prev number 를 가져오고,
            ++ , --
            next number로 save
        */

        // 3. 해당 강의가 존재하는지 체크
        if (existCourse.isEmpty()) {
            throw new CourseException("강의가 존재하지 않습니다",ErrorCode.COURSE_NOT_EXIST);
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
        int capacity = existCourse.get().getCapacity();
        int applicant = existCourse.get().getApplicant();
        if (applicant >= capacity) {
            throw new CourseException("정원이 꽉 찼습니다.", ErrorCode.OVER_CAPACITY);
        }
        int curCredit = existStudent.get().getCredits();
        if(curCredit+existCourse.get().getCredit() > 9){
            throw new UserException("수강 가능한 학점을 초과하였습니다. (9학점)", ErrorCode.OVER_CREDIT);
        }


        // 5. 수강 신청 정보 저장
        registerCourseRepository.save(
                RegisterCourse.builder().
                        registerCourseId(registerCourseId)
                        .build()
        );


        Course course = existCourse.get();
        course.setApplicant(course.getApplicant()+1);
        Student student=existStudent.get();
        student.setCredits(student.getCredits() + course.getCredit());

    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }

    }

    @Transactional
    public void cancel(RegisterCourseRequestDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_ID) == null) {
            throw new UserException("세션 정보가 없습니다.", ErrorCode.NONE_SESSION_INFORMATION);
        }

        int userId = (int) session.getAttribute(SESSION_ID);

        String rcId = dto.getCourseNumber() + "-" + dto.getClassNumber();

        RegisterCourseId registerCourseId= RegisterCourseId.builder()
                .courseId(rcId)
                .studentId(userId).build();
        registerCourseRepository.deleteById(registerCourseId);
        System.out.println("rcId : " + rcId);
        Optional<Course> existCourse = courseRepository.findById(rcId);

        Course course = existCourse.get();
        course.setApplicant(course.getApplicant()-1);
    }

    public List<Course> filter(SearchParam searchParam, HttpServletRequest request) {
        String major = searchParam.getMajor();
        int grade = searchParam.getGrade();
        String professor = searchParam.getProfessor();
        String name = searchParam.getName();
        String courseId = searchParam.getCourseId();
        Course course = new Course();
        System.out.println("courseId = " + courseId);
        System.out.println("name = " + name);
        System.out.println("professor = " + professor);
        System.out.println("grade = " + grade);
        System.out.println("major = " + major);

        return Collections.emptyList();
    }
}
