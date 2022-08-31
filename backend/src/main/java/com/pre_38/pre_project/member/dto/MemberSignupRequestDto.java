package com.pre_38.pre_project.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignupRequestDto {
    private Long memberId;
    private String username;
    private String password;
    private String email;

    public MemberSignupRequestDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
