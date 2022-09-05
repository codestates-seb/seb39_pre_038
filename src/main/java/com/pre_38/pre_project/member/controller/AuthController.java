package com.pre_38.pre_project.member.controller;

import com.pre_38.pre_project.dto.SingleResponseDto;
import com.pre_38.pre_project.member.dto.AccessTokenResponse;
import com.pre_38.pre_project.member.dto.BodyRequestDto;
import com.pre_38.pre_project.member.dto.TokenResponse;
import com.pre_38.pre_project.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private static final String REFRESH_TOKEN = "refreshToken";
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid BodyRequestDto bodyRequestDto) {

        TokenResponse tokenResponse = authService.createToken(bodyRequestDto.getCode());
        AccessTokenResponse response = new AccessTokenResponse(tokenResponse.getAccessToken(), authService.getExpireTime());

        ResponseCookie cookie = putTokenCookie(tokenResponse);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
        // cookie로 response body
    }

    @PostMapping("/refresh")
    public ResponseEntity refreshToken(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @RequestBody String accessToken) {
        return ResponseEntity
                .ok()
                .body(authService.refreshToken(request, response, accessToken));
    }

    private ResponseCookie putTokenCookie(TokenResponse tokenResponse) {
        return ResponseCookie.from(REFRESH_TOKEN, tokenResponse.getRefreshToken())
                .maxAge(14*24*3600)
                .path("/")
                .sameSite("None")
                .secure(true)
                .httpOnly(true)
                .build();
    }
}
