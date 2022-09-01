package com.pre_38.pre_project.member.service;

import com.pre_38.pre_project.member.jwt.JwtTokenProvider;
import com.pre_38.pre_project.member.repository.MemberRepository;
import com.pre_38.pre_project.member.support.CookieUtil;
import com.pre_38.pre_project.member.support.CustomUserDetails;
import lombok.RequiredArgsConstructor;
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
    private final JwtTokenProvider tokenProvider;

    public String refreshToken(HttpServletRequest request, HttpServletResponse response, String oldAccessToken) {
        String oldRefreshToken = CookieUtil.getCookie(request, cookieKey)
                .map(Cookie::getValue).orElseThrow(() -> new RuntimeException("no RefreshToken cookie"));

        if(!tokenProvider.validateToken(oldRefreshToken)) {
            throw new RuntimeException("Not Validated RefreshToken");
        }

        Authentication authentication = tokenProvider.getAuthentication(oldAccessToken);
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        Long id = Long.valueOf(user.getName());
        String savedToken = memberRepository.getRefreshTokenById(id);

        if(!savedToken.equals(oldRefreshToken)) {
            throw new RuntimeException("Not Matched RefreshToken");
        }

        String accessToken = tokenProvider.createAccessToken(authentication);
        tokenProvider.createRefreshToken(authentication, response);

        return accessToken;
    }
}
