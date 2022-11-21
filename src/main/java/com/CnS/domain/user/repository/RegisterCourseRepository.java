package com.CnS.domain.user.repository;

import com.CnS.domain.user.entity.RegisterCourse;
import com.CnS.domain.user.entity.RegisterCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterCourseRepository extends JpaRepository<RegisterCourse, RegisterCourseId> {

}
