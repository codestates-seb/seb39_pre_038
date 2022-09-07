package com.pre_38.pre_project.member.controller;

import com.pre_38.pre_project.member.auth.AuthorizationExtractor;
import com.pre_38.pre_project.member.token.TokenProvider;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;

    public AuthInterceptor(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AuthenticationException {
        if (isPreflight(request)) {
            return true;
        }

        String token = extractToken(request);
        tokenProvider.validateToken(token);

        return true;
    }

    private boolean isPreflight(final HttpServletRequest request) {
        return request.getMethod().equals(HttpMethod.OPTIONS.toString());
    }

    private String extractToken(HttpServletRequest request) {
            return AuthorizationExtractor.extractOrThrow(request);
    }
}