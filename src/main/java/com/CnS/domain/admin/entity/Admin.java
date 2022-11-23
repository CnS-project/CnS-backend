package com.CnS.domain.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;


@Getter
@Entity
@Table(name = "administrator")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @Column(name = "administrator_id")
    private Integer id;

    private String password;

    private Time startTime;

    private Time endTime;


}
