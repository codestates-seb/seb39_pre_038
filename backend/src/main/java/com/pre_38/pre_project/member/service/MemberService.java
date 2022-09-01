package com.pre_38.pre_project.member.service;

import com.pre_38.pre_project.exception.BusinessLogicException;
import com.pre_38.pre_project.exception.ExceptionCode;
import com.pre_38.pre_project.member.dto.MemberSignupRequestDto;
import com.pre_38.pre_project.member.entity.Member;
import com.pre_38.pre_project.member.repository.MemberRepository;
import com.pre_38.pre_project.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


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
    public Member findMember(String username){
        return findVerifiedMember(username);
    }

    //테이블에서 존재하는지 확인하는 메서드
    public Member findVerifiedMember(String username){
        Optional<Member> optionalMember =
                memberRepository.findByUsername(username);
        Member findMember =
                optionalMember.orElseThrow(() ->
<<<<<<< HEAD
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
=======
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
>>>>>>> main

        return findMember;
    }

}
