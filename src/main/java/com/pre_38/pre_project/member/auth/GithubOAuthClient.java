package com.pre_38.pre_project.member.auth;


import com.pre_38.pre_project.member.auth.dto.AccessTokenRequest;
import com.pre_38.pre_project.member.auth.dto.AccessTokenResponse;
import com.pre_38.pre_project.member.auth.dto.GithubMemberInfoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GithubOAuthClient implements OAuthClient{

    private static final String ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
    private static final String MEMBER_INFO_URL = "https://api.github.com/user";

    private static final RestTemplate restTemplate =new RestTemplate();

    private final String clientId;
    private final String clientSecret;

    public GithubOAuthClient(@Value("${security.oauth.github.client-id}") String clientId,
                             @Value("${security.oauth.github.client-secret}")String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public AccessTokenResponse getAccessToken(String code) {
        return restTemplate.postForObject(
                ACCESS_TOKEN_URL,
                new AccessTokenRequest(clientId, clientSecret, code),
                AccessTokenResponse.class);
    }

    @Override
    public GithubMemberInfoResponse getMemberInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        return restTemplate.exchange(
                MEMBER_INFO_URL,
                HttpMethod.GET,
                request,
                GithubMemberInfoResponse.class)
                .getBody();
    }
}
