package com.pre_38.pre_project.member.mapper;

import com.pre_38.pre_project.member.dto.MemberSignupRequestDto;
import com.pre_38.pre_project.member.entity.Member;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-02T14:14:23+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.14 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member MemberSignUpToMember(MemberSignupRequestDto memberSignupRequestDto) {
        if ( memberSignupRequestDto == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.username( memberSignupRequestDto.getUsername() );
        member.password( memberSignupRequestDto.getPassword() );
        member.email( memberSignupRequestDto.getEmail() );
        member.avatar( memberSignupRequestDto.getAvatar() );

        return member.build();
    }
}
