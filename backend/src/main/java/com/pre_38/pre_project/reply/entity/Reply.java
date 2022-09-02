package com.pre_38.pre_project.reply.entity;

import com.pre_38.pre_project.member.entity.Member;
import com.pre_38.pre_project.question.entity.Question;
import com.pre_38.pre_project.member.entity.Member;
import com.pre_38.pre_project.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @Column(nullable = false)
    private long votes;


    // 답글 : 게시판 = N : 1 양방향
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    public void setQuestion(Question question){
        this.question = question;
        if(!question.getReplies().contains(this))
            question.getReplies().add(this);
        if(!question.getReplies().contains(this))
            question.getReplies().add(this);
    }

    // 답글 : 회원 = 1 : 1 단방향
    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void setMember(Member member){
        this.member = member;
    }

    public Reply(Long replyId, String content, long votes) {
        this.replyId = replyId;
        this.content = content;
        this.votes = votes;
    }
}

