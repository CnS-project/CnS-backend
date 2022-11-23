package com.CnS.domain.admin.controller;

import com.CnS.domain.admin.dto.*;
import com.CnS.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/admin/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto dto, HttpServletRequest request) {
        adminService.login(dto, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


    @PostMapping("/courses")
    public ResponseEntity<Void> insertCourse(
            @RequestBody CourseInsertRequestDto courseInsertRequestDto , HttpServletRequest request) {
        adminService.insertCourse(courseInsertRequestDto, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/courses")
    public ResponseEntity<Void> modifyCourse(
            @RequestBody CourseModifyRequestDto courseModifyRequestDto, HttpServletRequest request) {
        adminService.modifyCourse(courseModifyRequestDto, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/admin/timing")
    public ResponseEntity<Void> modifyServerTime(@RequestBody TimeDto dto, HttpServletRequest request) {
        adminService.modifyServerTime(dto, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/courses")
    public ResponseEntity<Void> deleteCourse(
            @RequestBody CourseDeleteRequestDto courseDeleteRequestDto, HttpServletRequest request) {
        adminService.deleteCourse(courseDeleteRequestDto, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
