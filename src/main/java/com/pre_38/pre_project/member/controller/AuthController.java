package com.pre_38.pre_project.member.controller;

import com.pre_38.pre_project.member.auth.Auth;
import com.pre_38.pre_project.member.controller.dto.MemberResponse;
import com.pre_38.pre_project.member.controller.dto.SigninRequest;
import com.pre_38.pre_project.member.controller.dto.SigninResponse;
import com.pre_38.pre_project.member.service.AuthService;
import com.pre_38.pre_project.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/user")
    public ResponseEntity<SigninResponse> signin(@RequestBody SigninRequest signinRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signin(signinRequest));
    }
}
