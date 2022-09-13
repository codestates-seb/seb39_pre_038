package com.pre_38.pre_project.member.controller;

import com.pre_38.pre_project.member.auth.Auth;
import com.pre_38.pre_project.member.auth.AuthorizationExtractor;
import com.pre_38.pre_project.member.token.TokenProvider;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthResolver implements HandlerMethodArgumentResolver {

    private final TokenProvider tokenProvider;

    public AuthResolver(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Auth.class);
    }

    @Override
    public Long resolveArgument(MethodParameter parameter,
                                ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest,
                                WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        String token = extractToken(request);
        String payload = tokenProvider.parsePayload(token);
        return Long.parseLong(payload);
    }

    private String extractToken(HttpServletRequest request) {
        return AuthorizationExtractor.extractOrThrow(request);
    }
}