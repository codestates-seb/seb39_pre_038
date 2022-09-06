package com.pre_38.pre_project.member.service;

import com.pre_38.pre_project.member.dto.TokenResponse;
import com.pre_38.pre_project.member.repository.MemberRepository;
import com.pre_38.pre_project.member.support.CustomUserDetails;
import com.pre_38.pre_project.member.token.JwtTokenProvider;
import com.pre_38.pre_project.member.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${app.auth.token.refresh-cookie-key}")
    private String cookieKey;

    private final MemberRepository memberRepository;

    private final MemberService memberService;

    private final JwtTokenProvider tokenProvider;

    public TokenResponse createToken(String code) {
        //oauthclient 로 accesstoken(code) accessToken 으로 선언
        // 프로필 받아보기
        return null;

    }
    public String refreshToken(HttpServletRequest request,
                               HttpServletResponse response,
                               String oldAccessToken) {
        return null;
    }

    public long getExpireTime() {
        return tokenProvider.getValiditySeconds();
    }
}
