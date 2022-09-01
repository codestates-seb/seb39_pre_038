package com.pre_38.pre_project.member.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, length = 20)
    private String username;

    //@Column(nullable = false)
    private String avatar;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    private String refreshToken;

    //@Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @Builder
    public Member(Long memberId, String username, String avatar, String email, Role role, AuthProvider authProvider) {
        this.memberId = memberId;
        this.username = username;
        this.avatar = avatar;
        this.email = email;
        this.authProvider = authProvider;
        this.role = role;
    }
}
