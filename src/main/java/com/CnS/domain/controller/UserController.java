package com.CnS.domain.controller;

import com.CnS.domain.entity.Student;
import com.CnS.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<Student> login(@RequestBody Student s) {
        return ResponseEntity.ok( userService.login(s));
    }
}
