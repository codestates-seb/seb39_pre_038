package com.pre_38.pre_project.question.dto;

import com.pre_38.pre_project.member.dto.MemberDtoresponse;
import com.pre_38.pre_project.member.entity.Member;
import com.pre_38.pre_project.reply.dto.ReplyDto;
import com.pre_38.pre_project.reply.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {

    @Getter
    @AllArgsConstructor
    public static class Post{
        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;

        @NotEmpty(message = "내용을 입력하셔야 합니다.")
        private String content;

        @Email
        private String email;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch{
        private long questionId;
        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;
        @NotEmpty(message = "내용을 입력하셔야 합니다.")
        private String content;
        @Email
        private String email;
    }

    @Getter
    @AllArgsConstructor
    public static class response{
        private long questionId;
        private String title;
        private String content;
        private LocalDateTime date;
        private MemberDtoresponse member;
        //List<Reply>시 무한루프, 에러 발생, 엔티티 <-> DTO 계층 구분 확실히 해둘 것
        private List<ReplyDto.response> replies;
    }

    @Getter
    @Builder
    public static class responses{
        private long questionId;
        private String title;
        private String content;
        private LocalDateTime date;
        private MemberDtoresponse member;
        private long replies;
    }
}
