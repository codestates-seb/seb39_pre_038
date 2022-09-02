package com.pre_38.pre_project.member;

import com.pre_38.pre_project.member.dto.MemberSignupRequestDto;
import com.pre_38.pre_project.member.repository.MemberRepository;
import com.pre_38.pre_project.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 회원가입_테스트() {
        MemberSignupRequestDto signupDto = new MemberSignupRequestDto(
                "username", "password", "test@gmail.com");

        memberService.signup(signupDto);

        Long result = memberRepository.findByUsername("username").get().getMemberId();

        assertThat(result).isEqualTo(1);
    }
}
