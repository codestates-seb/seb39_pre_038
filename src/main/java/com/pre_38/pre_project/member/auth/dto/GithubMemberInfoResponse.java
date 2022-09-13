package com.pre_38.pre_project.member.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pre_38.pre_project.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GithubMemberInfoResponse {
    @JsonProperty("id")
    private String oauthId;

    @JsonProperty("login")
    private String username;

//    private String email; 필요한지 확인 필요
    @JsonProperty("avatar_url")
    private String avatar;


    public Member toMember() {
        return Member.builder()
                .oauthId(oauthId)
                .username(username)
                .avatar(avatar)
                .build();
    }
}
