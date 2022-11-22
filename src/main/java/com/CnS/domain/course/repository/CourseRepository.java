package com.CnS.domain.course.repository;

import com.CnS.domain.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CourseRepository extends JpaRepository<Course, String> {

    @Query("select c from Course c where c.courseId in :coursesId")
    List<Course> findAllByCoursesId(@Param("coursesId") List<String> courses_Id);

}
