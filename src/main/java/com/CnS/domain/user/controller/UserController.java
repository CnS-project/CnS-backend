package com.CnS.domain.user.controller;

import com.CnS.domain.user.dto.LoginDto;
import com.CnS.domain.user.dto.RegisterCourseRequestDto;
import com.CnS.domain.user.service.UserService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/users/{student-id}/courses")
//    public ResponseEntity<Student> inquire(@PathVariable("student-id") Integer id) {
//
//    }
}
