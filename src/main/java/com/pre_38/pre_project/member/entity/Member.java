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

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;

    //@Column(nullable = false)
    private String avatar;

    @Column(nullable = false)
    private String email;

    //@Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @Builder
    public Member(Long memberId, String username, String password, String email) {
        this.memberId = memberId;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
