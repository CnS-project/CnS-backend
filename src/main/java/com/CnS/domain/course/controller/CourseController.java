package com.CnS.domain.course.controller;

import com.CnS.domain.course.dto.CourseInquiryResponseDto;
import com.CnS.domain.course.dto.CourseInsertRequestDto;
import com.CnS.domain.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("")
    public ResponseEntity<CourseInquiryResponseDto> getCourses() {
        return ResponseEntity.ok(courseService.getCourses());
    }

    @PostMapping("")
    public ResponseEntity<Void> insertCourse(@RequestBody CourseInsertRequestDto courseInsertRequestDto) {
        courseService.insertCourse(courseInsertRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}