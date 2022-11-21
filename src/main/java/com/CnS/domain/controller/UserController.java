package com.CnS.domain.controller;

import com.CnS.domain.dto.LoginDto;
import com.CnS.domain.entity.Student;
import com.CnS.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<Student> login(@RequestBody LoginDto dto) {
        return ResponseEntity.ok(userService.login(dto));
    }

//    @GetMapping("/users/{student-id}/courses")
//    public ResponseEntity<Student> inquire(@PathVariable("student-id") Integer id) {
//
//    }
}
