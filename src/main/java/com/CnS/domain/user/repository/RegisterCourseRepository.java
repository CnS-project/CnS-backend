package com.CnS.domain.user.repository;

import com.CnS.domain.user.entity.RegisterCourse;
import com.CnS.domain.user.entity.RegisterCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterCourseRepository extends JpaRepository<RegisterCourse, RegisterCourseId> {
    @Query("select rc.registerCourseId.courseId from RegisterCourse rc where rc.registerCourseId.studentId = :studentId")
    List<String> findByStudentId(@Param("studentId") Integer student_id);
}
