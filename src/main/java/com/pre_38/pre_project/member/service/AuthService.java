package com.pre_38.pre_project.member.service;

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

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${app.auth.token.refresh-cookie-key}")
    private String cookieKey;

    private final MemberRepository memberRepository;

    private final JwtTokenProvider tokenProvider;

    public String refreshToken(HttpServletRequest request,
                               HttpServletResponse response,
                               String oldAccessToken) {
        String oldRefreshToken = CookieUtil.getCookie(request, cookieKey)
                .map(Cookie::getValue)
                .orElseThrow(() -> new RuntimeException("no Refresh token cookie"));

        if(!tokenProvider.validateToken(oldRefreshToken)) {
            throw new RuntimeException("Not validated refresh token");
        }

        Authentication authentication = tokenProvider.getAuthentication(oldAccessToken);
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        Long id = Long.valueOf(user.getName());

        String savedToken = memberRepository.getRefreshTokenById(id);

        if(!savedToken.equals(oldRefreshToken)) {
            throw new RuntimeException("Not matched Refresh Token");
        }

        String accessToken = tokenProvider.createAccessToken(authentication);
        tokenProvider.createRefreshToken(authentication, response);

        return accessToken;
    }
}
