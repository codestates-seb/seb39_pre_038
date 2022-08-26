package com.pre_38.pre_project.reply.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
public class ReplyDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotEmpty(message = "내용을 입력하셔야 합니다.")
        private String content;

        private String name;
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
        private Question question;
        private Member member;
        private String content;
        private LocalDateTime date;
        private long votes;
    }
}
