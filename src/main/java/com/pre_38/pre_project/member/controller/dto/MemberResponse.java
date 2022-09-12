package com.pre_38.pre_project.member.controller.dto;

import com.pre_38.pre_project.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberResponse {
    private Long memberId;
    private String username;
    private String email;
    private String avatar;

    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getMemberId(),
                member.getUsername(),
                member.getEmail(),
                member.getAvatar()
        );
    }
}
