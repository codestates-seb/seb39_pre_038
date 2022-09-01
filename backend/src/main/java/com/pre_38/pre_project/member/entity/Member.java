package com.pre_38.pre_project.member.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String username;
    //@Column(nullable = false)
    private String avatar;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;
    private String refreshToken;
    //@Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @Builder
    public Member(String email, String avatar, Role role, AuthProvider authProvider) {
        this.email = email;
        this.role = role;
        this.avatar = avatar;
        this.authProvider = authProvider;
    }
}
