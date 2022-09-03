package com.pre_38.pre_project.question.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pre_38.pre_project.member.entity.Member;
import com.pre_38.pre_project.reply.entity.Reply;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne// 게시글 : 회원 = N : 1 단방향
    @JoinColumn(name="member_id")
    private Member member;

    //게시글 : 답글 = 1 : N 양방향
    //Question 삭제 -> 해당 Question의 Reply도 삭제
    @OneToMany(mappedBy = "question", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}) // 1:N 양방향
    private List<Reply> replies = new ArrayList<>();

    public void setMember(Member member){
        this.member = member;
    }

    public void setReply(Reply reply){
        replies.add(reply);
        if(reply.getQuestion() != this)
            reply.setQuestion(this);
    }

    public Question(Long questionId, String title, String content, int votes){
        this.questionId = questionId;
        this.title = title;
        this.content = content;
        this.votes = votes;
    }
}
