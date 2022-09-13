package com.pre_38.pre_project.member.service;

import com.pre_38.pre_project.member.auth.OAuthClient;
import com.pre_38.pre_project.member.auth.dto.AccessTokenResponse;
import com.pre_38.pre_project.member.auth.dto.GithubMemberInfoResponse;
import com.pre_38.pre_project.member.controller.dto.SigninRequest;
import com.pre_38.pre_project.member.controller.dto.SigninResponse;
import com.pre_38.pre_project.member.entity.Member;
import com.pre_38.pre_project.member.repository.MemberRepository;
import com.pre_38.pre_project.member.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final OAuthClient oAuthClient;
    private final TokenProvider tokenProvider;

    public SigninResponse signin(SigninRequest signinRequest) {
        GithubMemberInfoResponse memberInfoResponse = getOAuthMemberInfo(signinRequest.getCode());

        Member member = findOrSaveMember(memberInfoResponse);

        String token = tokenProvider.createToken(String.valueOf(member.getMemberId()));
        return new SigninResponse(token);
    }

    private GithubMemberInfoResponse getOAuthMemberInfo(String code) {
        AccessTokenResponse tokenResponse = oAuthClient.getAccessToken(code);
        return oAuthClient.getMemberInfo(tokenResponse.getAccessToken());
    }

    private Member findOrSaveMember(GithubMemberInfoResponse memberInfoResponse) {
        return memberRepository.findByOauthId(memberInfoResponse.getOauthId())
                .orElseGet(
                        () -> memberRepository.save(memberInfoResponse.toMember()));
    }
}
