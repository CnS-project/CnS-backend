package com.CnS.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class coursesIdOfUserDto {
    List<String> coursesIds;

}
