package com.CnS.domain.user.service;

import static com.CnS.global.constant.SessionConstants.SESSION_ID;

import com.CnS.domain.admin.entity.Time;
import com.CnS.domain.admin.repository.TimeRepository;
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
import com.CnS.global.error.exception.ServerException;
import com.CnS.global.error.exception.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final RegisterCourseRepository registerCourseRepository;
    private final TimeRepository timeRepository;

    public boolean serverTimeValidation() {
        List<Time> all = timeRepository.findAll();
        Time time = all.get(0);
        java.sql.Time endTime = time.getEndTime();
        java.sql.Time startTime = time.getStartTime();
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        String s = "";
        s += hour + ":" +minute+":"+second;

        java.sql.Time compareTime = java.sql.Time.valueOf(s);
        if (compareTime.before(endTime) && compareTime.after(startTime)) {
            System.out.println(" Ok ");
            return true;
        }else{
            System.out.println("false");
            return false;
        }
    }
    @Transactional
    public List<Course> courseList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_ID) == null) {
            throw new UserException("?????? ????????? ????????????.", ErrorCode.NONE_SESSION_INFORMATION);
        }

        int id = (int) session.getAttribute(SESSION_ID);

        List<String> coursesId = registerCourseRepository.findByStudentId(id);
        if (!coursesId.isEmpty()) {
            List<Course> allByCoursesId = courseRepository.findAllByCoursesId(coursesId);

            return allByCoursesId;

        }

        return Collections.emptyList();
    }
    @Transactional
    public void login(LoginDto dto, HttpServletRequest request) {
        serverTimeValidation();

        Optional<Student> existStudent = userRepository.findById(dto.getStudentId());
        if (existStudent.isPresent()) {
            String password = existStudent.get().getPassword();
            if (dto.getPassword().equals(password)) {
                Student student = existStudent.get();

                HttpSession session = request.getSession();
                session.setAttribute(SESSION_ID, student.getStudentId());
            } else {
                throw new UserException("??????????????? ?????????????????????.", ErrorCode.INVALID_USER_PASSWORD);
            }

        } else {
            throw new UserException("???????????? ?????????????????????.", ErrorCode.INVALID_USER_ID);
        }
    }

    @Transactional
    public void registerCourse(RegisterCourseRequestDto registerCourseRequestDto,
                               HttpServletRequest request) {
        if (!serverTimeValidation()) {
            //?????? ?????? ?????? ?????? ?????? ???,
            throw new ServerException("?????? ??????/?????? ?????? ????????? ????????????.", ErrorCode.INVALID_SERVER_TIME);
        }

        // 1. ???????????? ???????????? ??????
        HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_ID) == null) {
            throw new UserException("?????? ????????? ????????????.", ErrorCode.NONE_SESSION_INFORMATION);
        }

        int userId = (int) session.getAttribute(SESSION_ID);

        // 2. ?????? ?????? ??????
        String courseId = registerCourseRequestDto.getCourseNumber() + "-" +
                registerCourseRequestDto.getClassNumber();

        Optional<Course> existCourse = courseRepository.findById(courseId);
        Optional<Student> existStudent = userRepository.findById(userId);
        /*
            prev number ??? ????????????,
            ++ , --
            next number??? save
        */

        // 3. ?????? ????????? ??????????????? ??????
        if (existCourse.isEmpty()) {
            throw new CourseException("????????? ???????????? ????????????",ErrorCode.COURSE_NOT_EXIST);
        }

        RegisterCourseId registerCourseId = RegisterCourseId.builder()
                .courseId(courseId)
                .studentId(userId).build();

        Optional<RegisterCourse> existRegisterCourse = registerCourseRepository.findById(
                registerCourseId);

        // 4. ?????? ????????? ???????????? ??????
        if (existRegisterCourse.isPresent()) {
            throw new UserException("?????? ????????? ???????????????.", ErrorCode.DUPLICATE_REGISTER);
        }
        int capacity = existCourse.get().getCapacity();
        int applicant = existCourse.get().getApplicant();
        if (applicant >= capacity) {
            throw new CourseException("????????? ??? ????????????.", ErrorCode.OVER_CAPACITY);
        }
        int curCredit = existStudent.get().getCredits();
        if (curCredit + existCourse.get().getCredit() > 9) {
            throw new UserException("?????? ????????? ????????? ?????????????????????. (9??????)", ErrorCode.OVER_CREDIT);
        }
        // major ?????? ??????.
        if (!existCourse.get().getMajor().equals(existStudent.get().getMajor())) {
            throw new UserException("?????? ?????? ????????? ????????????.", ErrorCode.INVALID_MAJOR);
        }


        // 5. ?????? ?????? ?????? ??????
        registerCourseRepository.save(
                RegisterCourse.builder().
                        registerCourseId(registerCourseId)
                        .build()
        );


        Course course = existCourse.get();
        course.setApplicant(course.getApplicant() + 1);
        Student student = existStudent.get();
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
        if (!serverTimeValidation()) {
            //?????? ?????? ?????? ?????? ?????? ???,
            throw new ServerException("?????? ??????/?????? ?????? ????????? ????????????.", ErrorCode.INVALID_SERVER_TIME);
        }
        HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_ID) == null) {
            throw new UserException("?????? ????????? ????????????.", ErrorCode.NONE_SESSION_INFORMATION);
        }

        int userId = (int) session.getAttribute(SESSION_ID);

        String rcId = dto.getCourseNumber() + "-" + dto.getClassNumber();

        RegisterCourseId registerCourseId = RegisterCourseId.builder()
                .courseId(rcId)
                .studentId(userId).build();
        registerCourseRepository.deleteById(registerCourseId);
        System.out.println("rcId : " + rcId);
        Optional<Course> existCourse = courseRepository.findById(rcId);
        Optional<Student> existStudent = userRepository.findById(userId);

        Student student = existStudent.get();
        Course course = existCourse.get();
        student.setCredits(student.getCredits() - course.getCredit());
        course.setApplicant(course.getApplicant() - 1);
    }
    @Transactional
    public List<Course> filter(SearchParam searchParam, HttpServletRequest request) {
        String major = "%" + searchParam.getMajor() + "%";
        String professor = "%" + searchParam.getProfessor() + "%";
        String name = "%" + searchParam.getName() + "%";
        Integer grade = searchParam.getGrade();
        Integer courseNumber = searchParam.getCourseNumber();

        //grade, courseNumber ??????
        List<Course> allByConditions;
        if (grade != null && courseNumber != null) {
            allByConditions = courseRepository.findAllByConditionsWithTwo(courseNumber, name, professor, major, courseNumber);
        } else if (grade != null && courseNumber == null) {
            allByConditions = courseRepository.findAllByConditionsWithGrade(name, professor, major, grade);
        } else if (grade == null && courseNumber != null) {
            allByConditions = courseRepository.findAllByConditionsWithCourseNumber(courseNumber, name, professor, major);
        } else {
            allByConditions = courseRepository.findAllByConditionsWithZero(name, professor, major);
        }

        return allByConditions;
    }
}
