package com.pre_38.pre_project.member.auth;

import com.pre_38.pre_project.member.auth.dto.AccessTokenResponse;
import com.pre_38.pre_project.member.auth.dto.GithubMemberInfoResponse;

public interface OAuthClient {
    AccessTokenResponse getAccessToken(String code);

    GithubMemberInfoResponse getMemberInfo(String accessToken);
}
