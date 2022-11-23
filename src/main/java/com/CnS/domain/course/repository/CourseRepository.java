package com.CnS.domain.course.repository;

import com.CnS.domain.course.entity.Course;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CourseRepository extends JpaRepository<Course, String> {

    @Query("select c from Course c where c.courseId in :coursesId")
    List<Course> findAllByCoursesId(@Param("coursesId") List<String> courses_Id);


    // grade , courseNumber
    @Query("select c from Course c where c.targetGrade= :grade and c.courseNumber = :courseNumber and c.name like :name and c.professor like :professor and c.major like :major")
    List<Course> findAllByConditionsWithTwo(@Param("courseNumber") Integer courseNumber,
                                     @Param("name") String name,
                                     @Param("professor") String professor,
                                     @Param("major") String major,
                                     @Param("grade") Integer grade
    );

    // grade
    @Query("select c from Course c where c.targetGrade = :grade and c.name like :name and c.professor like :professor and c.major like :major")
    List<Course> findAllByConditionsWithGrade(
            @Param("name") String name,
            @Param("professor") String professor,
            @Param("major") String major,
            @Param("grade") Integer grade);

    // courseNumber
    @Query("select c from Course c where c.courseNumber = :courseNumber and c.name like :name and c.professor like :professor and c.major like :major")
    List<Course> findAllByConditionsWithCourseNumber(@Param("courseNumber") Integer courseNumber,
                                     @Param("name") String name,
                                     @Param("professor") String professor,
                                     @Param("major") String major
                                     );

    // x
    @Query("select c from Course c where c.name like :name and c.professor like :professor and c.major like :major")
    List<Course> findAllByConditionsWithZero(@Param("name") String name,
                                     @Param("professor") String professor,
                                     @Param("major") String major
    );

}
