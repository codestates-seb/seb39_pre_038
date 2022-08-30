package com.pre_38.pre_project.member.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String username;

    private String password;

    //@Column(nullable = false)
    private String avatar;

    private String email;

    //@Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Member(Long memberId, String username, String password, String email) {
        this.memberId = memberId;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Member(Long memberId, String username, String avatar, String password, String email) {
        this.memberId = memberId;
        this.avatar = avatar;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getRoleValue() {
        return this.role.getValue();
    }
}
