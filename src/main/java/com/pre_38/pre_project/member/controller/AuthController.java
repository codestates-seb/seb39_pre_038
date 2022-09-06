package com.pre_38.pre_project.member.controller;

import com.pre_38.pre_project.member.dto.UserRequestDto;
import com.pre_38.pre_project.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/user")
    public ResponseEntity user(UserRequestDto userRequestDto) {
        String result = userRequestDto.getRequest();

        return new ResponseEntity(result, HttpStatus.OK);
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
