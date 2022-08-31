package com.pre_38.pre_project.reply.dto;

import com.pre_38.pre_project.member.entity.Member;
import com.pre_38.pre_project.question.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class ReplyDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotEmpty(message = "내용을 입력하셔야 합니다.")
        private String content;
        private String username;
        @NotNull
        private long questionId;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch{
        private long replyId;

        @NotEmpty(message = "내용을 입력하셔야 합니다.")
        private String content;

        private String name;

    public void setReplyId(long replyId){
        this.replyId = replyId;
        }
    }


    @Getter
    @AllArgsConstructor
    public static class response{
        private long replyId;
        private String content;
        private LocalDateTime date;
        private long votes;
        private Member member;
    }
}
