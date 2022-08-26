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

    @PostMapping("/member/signup")
    public ResponseEntity<Object> signupMember(
            @RequestBody MemberSignupRequestDto signupDto) {
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/member/login")
    public ResponseEntity<Object> login(
            @RequestBody MemberLoginDto loginDto) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
