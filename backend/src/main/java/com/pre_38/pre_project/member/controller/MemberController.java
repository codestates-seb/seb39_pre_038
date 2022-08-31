package com.pre_38.pre_project.member.controller;

import com.pre_38.pre_project.member.repository.MemberRepository;
import com.pre_38.pre_project.member.dto.MemberLoginDto;
import com.pre_38.pre_project.member.dto.MemberSignupRequestDto;
import com.pre_38.pre_project.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(
            @RequestBody MemberSignupRequestDto signupDto) {
        memberService.signup(signupDto);
        return new ResponseEntity<>("회원가입", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestBody MemberLoginDto loginDto) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout() {
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
