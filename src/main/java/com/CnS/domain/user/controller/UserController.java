package com.CnS.domain.user.controller;

import com.CnS.domain.user.dto.LoginDto;
import com.CnS.domain.user.dto.RegisterCourseRequestDto;
import com.CnS.domain.user.entity.Student;
import com.CnS.domain.user.service.UserService;
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
    public ResponseEntity<Student> login(@RequestBody LoginDto dto) {
        return ResponseEntity.ok(userService.login(dto));
    }

    @PostMapping("/registration-course")
    public ResponseEntity<Void> registerCourse(
        @RequestBody RegisterCourseRequestDto registerCourseRequestDto) {
        userService.registerCourse(registerCourseRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @GetMapping("/users/{student-id}/courses")
//    public ResponseEntity<Student> inquire(@PathVariable("student-id") Integer id) {
//
//    }
}
