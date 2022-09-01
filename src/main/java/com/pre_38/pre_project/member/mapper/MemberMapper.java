package com.pre_38.pre_project.member.mapper;

import com.pre_38.pre_project.member.dto.MemberSignupRequestDto;
import com.pre_38.pre_project.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member MemberSignUpToMember(MemberSignupRequestDto memberSignupRequestDto);
}
