package com.pre_38.pre_project.member.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccessTokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
}
