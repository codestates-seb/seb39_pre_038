package com.pre_38.pre_project.member.service;

import com.pre_38.pre_project.member.entity.Member;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemberService {

    //멤버 찾기용
    public Member findMember(String memberName){
        //임시로 만든 엔티티
        return new Member(1L,memberName,"아바타","gmail@gmail.com", LocalDateTime.now());
    }
}
