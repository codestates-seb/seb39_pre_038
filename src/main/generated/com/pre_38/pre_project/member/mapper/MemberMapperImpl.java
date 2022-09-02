package com.pre_38.pre_project.member.mapper;

<<<<<<< HEAD
import com.pre_38.pre_project.member.dto.MemberSignupRequestDto;
=======
import com.pre_38.pre_project.member.dto.MemberDtoresponse;
>>>>>>> 687f76890d74108b5518f0d40dedf28f91bdc486
import com.pre_38.pre_project.member.entity.Member;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2022-09-02T14:14:23+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.14 (Oracle Corporation)"
=======
    date = "2022-09-02T14:17:31+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
>>>>>>> 687f76890d74108b5518f0d40dedf28f91bdc486
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
<<<<<<< HEAD
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
=======
    public MemberDtoresponse MemberToMemberDtoresponse(Member member) {
        if ( member == null ) {
            return null;
        }

        String username = null;
        String avatar = null;

        username = member.getUsername();
        avatar = member.getAvatar();

        MemberDtoresponse memberDtoresponse = new MemberDtoresponse( username, avatar );

        return memberDtoresponse;
>>>>>>> 687f76890d74108b5518f0d40dedf28f91bdc486
    }
}
