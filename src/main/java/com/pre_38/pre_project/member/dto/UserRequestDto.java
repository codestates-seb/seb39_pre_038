package com.pre_38.pre_project.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UserRequestDto {
    @NotNull
    private String request;
}
