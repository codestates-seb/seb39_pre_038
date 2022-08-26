package com.pre_38.pre_project.member.dto;

import lombok.Getter;

@Getter
public class MemberSignupRequestDto {
    private Long memberId;
    private String name;
    private String email;
}
