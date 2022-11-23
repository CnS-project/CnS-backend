package com.CnS.domain.user.controller;

import com.CnS.domain.course.entity.Course;
import com.CnS.domain.user.dto.LoginDto;
import com.CnS.domain.user.dto.RegisterCourseRequestDto;
import com.CnS.domain.user.dto.SearchParam;
import com.CnS.domain.user.service.UserService;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    @GetMapping("/filtering")
    public ResponseEntity<List<Course>> filter(SearchParam searchParam, HttpServletRequest request) {
        return ResponseEntity.ok(userService.filter(searchParam, request));

    }

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

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> inquire(HttpServletRequest request) {
        return ResponseEntity.ok(userService.courseList(request));
    }

    @PutMapping("/courses/cancel")
    public ResponseEntity<Void> cancel(@RequestBody RegisterCourseRequestDto dto, HttpServletRequest request) {
        userService.cancel(dto, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        userService.logout(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
