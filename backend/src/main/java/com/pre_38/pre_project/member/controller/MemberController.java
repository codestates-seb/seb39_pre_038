package com.pre_38.pre_project.member.controller;

import com.pre_38.pre_project.member.entity.Member;
import com.pre_38.pre_project.member.repository.MemberRepository;
import com.pre_38.pre_project.member.support.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public Member getCurrentMember(@AuthenticationPrincipal CustomUserDetails user) {
        return memberRepository.findById(user.getId()).orElseThrow(() ->
        new IllegalStateException("not found user"));
    }
}
