package com.CnS.domain.admin.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "time")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Time {

    @Id
    private Integer id;

    private java.sql.Time startTime;

    private java.sql.Time endTime;

    public void setStartTime(java.sql.Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(java.sql.Time endTime) {
        this.endTime = endTime;
    }
}
