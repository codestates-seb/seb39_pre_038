package com.pre_38.pre_project.member.repository;

import com.pre_38.pre_project.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByOauthId(String oauthId);
}
