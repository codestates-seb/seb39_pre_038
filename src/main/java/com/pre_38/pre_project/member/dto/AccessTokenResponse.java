package com.pre_38.pre_project.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AccessTokenResponse {
    private final String accessToken;
    private final long expiredTime;
}
