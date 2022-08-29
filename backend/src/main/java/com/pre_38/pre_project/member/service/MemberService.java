package com.pre_38.pre_project.member.service;

import com.pre_38.pre_project.member.dto.MemberSignupRequestDto;
import com.pre_38.pre_project.member.entity.Member;
import com.pre_38.pre_project.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signup(MemberSignupRequestDto signupDto) {
        Member member = Member.builder()
                .username(signupDto.getUsername())
                .password(signupDto.getPassword())
                .email(signupDto.getEmail())
                .build();
        memberRepository.save(member);
    }
    //멤버 찾기용
    public Member findMember(String memberName){
        //임시로 만든 엔티티
        return new Member(1L,memberName,"아바타","gmail@gmail.com", LocalDateTime.now());
    }
}
