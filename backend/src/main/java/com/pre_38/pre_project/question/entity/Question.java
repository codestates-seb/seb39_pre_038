package com.pre_38.pre_project.question.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pre_38.pre_project.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false, updatable = true, length = 100)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @Column(nullable = false)
    private long votes;

    @OneToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    public void setMember(Member member){
        this.member = member;
    }

    public Question(Long questionId, String title, String content, int votes){
        this.questionId = questionId;
        this.title = title;
        this.content = content;
        this.votes = votes;
    }
}
