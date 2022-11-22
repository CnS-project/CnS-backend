package com.CnS.domain.user.controller;

import com.CnS.domain.course.entity.Course;
import com.CnS.domain.user.dto.LoginDto;
import com.CnS.domain.user.dto.RegisterCourseRequestDto;
import com.CnS.domain.user.service.UserService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto dto, HttpServletRequest request) {
        userService.login(dto, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/registration-course")
    public ResponseEntity<Void> registerCourse(
            @RequestBody RegisterCourseRequestDto registerCourseRequestDto,
            HttpServletRequest request) {
        userService.registerCourse(registerCourseRequestDto, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{student-id}/courses")
    public ResponseEntity<List<Course>> inquire(@PathVariable("student-id") Integer id) {
        return ResponseEntity.ok(userService.courseList(id));
    }

    @PutMapping("/courses/cancel")
    public void cancel() {

    }
    
    @PutMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        userService.logout(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
