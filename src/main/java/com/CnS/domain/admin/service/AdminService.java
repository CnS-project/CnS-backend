package com.CnS.domain.admin.service;

import com.CnS.domain.admin.dto.*;
import com.CnS.domain.admin.entity.Admin;
import com.CnS.domain.admin.entity.Time;
import com.CnS.domain.admin.repository.AdminRepository;
import com.CnS.domain.admin.repository.TimeRepository;
import com.CnS.domain.course.entity.Course;
import com.CnS.domain.course.repository.CourseRepository;
import com.CnS.global.error.exception.CourseException;
import com.CnS.global.error.exception.ErrorCode;
import com.CnS.global.error.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.CnS.global.constant.SessionConstants.SESSION_ID;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final CourseRepository courseRepository;
    private final TimeRepository timeRepository;
    @Transactional
    public void insertCourse(CourseInsertRequestDto courseInsertRequestDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_ID) == null) {
            throw new UserException("세션 정보가 없습니다.", ErrorCode.NONE_SESSION_INFORMATION);
        }

        int userId = (int) session.getAttribute(SESSION_ID);
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
    public void modifyCourse(CourseModifyRequestDto courseModifyRequestDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_ID) == null) {
            throw new UserException("세션 정보가 없습니다.", ErrorCode.NONE_SESSION_INFORMATION);
        }

        int userId = (int) session.getAttribute(SESSION_ID);
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
                        .applicant(existCourse.get().getApplicant())
                        .credit(existCourse.get().getCredit())
                        .major(existCourse.get().getMajor())
                        .targetGrade(existCourse.get().getTargetGrade())
                        .professor(existCourse.get().getProfessor())
                        .courseId(courseId)
                        .name(courseModifyRequestDto.getName())
                        .courseNumber(courseModifyRequestDto.getCourseNumber())
                        .classNumber(courseModifyRequestDto.getClassNumber())
                        .capacity(courseModifyRequestDto.getCapacity())
                        .build()
        );
    }
    @Transactional
    public void deleteCourse(CourseDeleteRequestDto courseDeleteRequestDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_ID) == null) {
            throw new UserException("세션 정보가 없습니다.", ErrorCode.NONE_SESSION_INFORMATION);
        }

        int userId = (int) session.getAttribute(SESSION_ID);
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
    @Transactional
    public void login(LoginDto dto, HttpServletRequest request) {

        Optional<Admin> existAdmin = adminRepository.findById(dto.getAdministratorId());
        if (existAdmin.isPresent()) {
            String password = existAdmin.get().getPassword();
            if (dto.getPassword().equals(password)) {
                Admin admin = existAdmin.get();

                HttpSession session = request.getSession();
                session.setAttribute(SESSION_ID, admin.getId());
            } else {
                throw new UserException("패스워드가 잘못되었습니다.", ErrorCode.INVALID_USER_PASSWORD);
            }

        } else {
            throw new UserException("아이디가 잘못되었습니다.", ErrorCode.INVALID_USER_ID);
        }
    }

    @Transactional
    public void modifyServerTime(TimeDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_ID) == null) {
            throw new UserException("세션 정보가 없습니다.", ErrorCode.NONE_SESSION_INFORMATION);
        }

        int userId = (int) session.getAttribute(SESSION_ID);
        Optional<Time> existTime = timeRepository.findById(userId);
        if (existTime.isPresent()) {
            System.out.println("existTime = " + existTime);
            Time time = existTime.get();
            
            time.setEndTime(dto.getEndTime());
            System.out.println("dto.getEndTime() = " + dto.getEndTime());
            time.setStartTime(dto.getStartTime());

        }
    }
}
