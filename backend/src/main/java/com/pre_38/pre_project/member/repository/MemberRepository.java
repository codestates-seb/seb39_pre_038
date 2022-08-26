package com.pre_38.pre_project.member.repository;

import com.pre_38.pre_project.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
