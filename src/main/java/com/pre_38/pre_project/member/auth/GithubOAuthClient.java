package com.pre_38.pre_project.member.auth;

import com.pre_38.pre_project.member.auth.dto.AccessTokenResponse;
import com.pre_38.pre_project.member.auth.dto.GithubMemberInfoResponse;

public class GithubOAuthClient implements OAuthClient{

    // acceessToken 요청 url "https://github.com/login/oauth/access_token"
    // memberInfo 요청 url "https://api.github.com/user"

    // clientId
    // clientSecret

    // 생성자 @Value 애너테이션으로 값 지정
    // security.oauth.github.client-id
    // security.oauth.github.client-secret

    @Override
    public AccessTokenResponse getAccessToken(String code) {
        // POST 요청으로 받은 code로 AccessToken 교환
        // accessTokenUrl,(clientId, clientSecret, code)
        // restTemplate postForObject 메서드로 리턴
        return null;
    }

    @Override
    public GithubMemberInfoResponse getMemberInfo(String accessToken) {
        // 발급받은 액세스 토큰을 이용해 resource server에서 유저의 정보 받아오기
        // header에 bearer 타입으로 지정
        // restTemplate exchange 메서드로 리턴(HTTP GET 통신)
        return null;
    }
}
