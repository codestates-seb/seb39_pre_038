package com.pre_38.pre_project.member.controller;

import com.pre_38.pre_project.member.controller.dto.SigninRequest;
import com.pre_38.pre_project.member.controller.dto.SigninResponse;
import com.pre_38.pre_project.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<SigninResponse> signin(@RequestBody SigninRequest signinRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signin(signinRequest));
    }
}
