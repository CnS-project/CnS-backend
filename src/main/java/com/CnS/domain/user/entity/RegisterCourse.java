package com.CnS.domain.user.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "register_course")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCourse {

    @EmbeddedId
    private RegisterCourseId registerCourseId;

}
