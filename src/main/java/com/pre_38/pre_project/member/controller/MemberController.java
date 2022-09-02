package com.pre_38.pre_project.member.controller;

import com.pre_38.pre_project.member.entity.Member;
import com.pre_38.pre_project.member.repository.MemberRepository;
import com.pre_38.pre_project.member.dto.MemberLoginDto;
import com.pre_38.pre_project.member.dto.MemberSignupRequestDto;
import com.pre_38.pre_project.member.service.MemberService;
import com.pre_38.pre_project.member.support.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@Log4j2
@RequestMapping("/user")
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public Member getCurrentMember(@AuthenticationPrincipal CustomUserDetails member) {
        return memberRepository.findById(member.getId()).orElseThrow(
                () -> new IllegalStateException("not found member"));
    }
}
