package com.pre_38.pre_project.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class BodyRequestDto {

    @NotNull
    private String code;
}
