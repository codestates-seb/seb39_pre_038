package com.pre_38.pre_project.member.service;

import com.pre_38.pre_project.exception.BusinessLogicException;
import com.pre_38.pre_project.exception.ExceptionCode;
import com.pre_38.pre_project.member.entity.Member;
import com.pre_38.pre_project.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //멤버 찾기용
    public Member findMember(String email){
        return findVerifiedMember(email);
    }

    //테이블에서 존재하는지 확인하는 메서드
    public Member findVerifiedMember(String email){
        Optional<Member> optionalMember =
                memberRepository.findByEmail(email);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }

}
