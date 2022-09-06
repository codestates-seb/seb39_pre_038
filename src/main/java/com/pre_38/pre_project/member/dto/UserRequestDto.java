package com.pre_38.pre_project.member.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@JsonAutoDetect
public class UserRequestDto {
    @NotNull
    private String code;
}
