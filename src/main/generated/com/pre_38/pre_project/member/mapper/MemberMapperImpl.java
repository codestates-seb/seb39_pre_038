package com.pre_38.pre_project.member.mapper;

import com.pre_38.pre_project.member.dto.MemberDtoresponse;
import com.pre_38.pre_project.member.entity.Member;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-02T14:17:31+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
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
    }
}
