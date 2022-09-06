package com.pre_38.pre_project.member.controller;

import com.pre_38.pre_project.dto.SingleResponseDto;
import com.pre_38.pre_project.member.dto.BodyRequestDto;
import com.pre_38.pre_project.member.dto.UserRequestDto;
import com.pre_38.pre_project.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    private final AuthService authService;
    @PostMapping("/user")
    public ResponseEntity user(@RequestBody String code) {
        log.info(code);
        return new ResponseEntity(new SingleResponseDto<>(code), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity refreshToken(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @RequestBody String accessToken) {
        return ResponseEntity
                .ok()
                .body(authService.refreshToken(request, response, accessToken));
    }
}
