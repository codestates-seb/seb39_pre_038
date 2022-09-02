package com.pre_38.pre_project.member.service;

import com.pre_38.pre_project.member.entity.AuthProvider;
import com.pre_38.pre_project.member.entity.Member;
import com.pre_38.pre_project.member.entity.Role;
import com.pre_38.pre_project.member.exception.OAuthProcessingException;
import com.pre_38.pre_project.member.repository.MemberRepository;
import com.pre_38.pre_project.member.support.CustomUserDetails;
import com.pre_38.pre_project.member.support.info.OAuth2UserInfo;
import com.pre_38.pre_project.member.support.info.OAuth2UserInfoFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.time.LocalDateTime.now;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        return process(oAuth2UserRequest, oAuth2User);
    }

    private OAuth2User process(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        AuthProvider authProvider = AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase());
        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(authProvider, oAuth2User.getAttributes());

        if(userInfo.getEmail().isEmpty()) {
            throw new OAuthProcessingException("Email not found from OAuth provider");
        }
        Optional<Member> memberOptional = memberRepository.findByEmail(userInfo.getEmail());
        Member member;

        if(memberOptional.isPresent()) {
            member = memberOptional.get();
            if(authProvider != member.getAuthProvider()) {
                throw new OAuthProcessingException("Wrong match Oauth provider");
            }
        } else {
            member = createMember(userInfo, authProvider);
        }
        return CustomUserDetails.create(member, oAuth2User.getAttributes());
        }

        private Member createMember(OAuth2UserInfo userInfo, AuthProvider authProvider) {
        Member member = Member.builder()
                .email(userInfo.getEmail())
                .username(userInfo.getName())
                .avatar(userInfo.getAvatar())
                .role(Role.USER)
                .date(now())
                .authProvider(authProvider)
                .build();
        return memberRepository.save(member);
    }
}
