package com.pre_38.pre_project.question.dto;

import com.pre_38.pre_project.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class QuestionDto {

    @Getter
    @AllArgsConstructor
    public static class Post{
        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;

        @NotEmpty(message = "내용을 입력하셔야 합니다.")
        private String content;

        private String name;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch{
        private long questionId;

        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;

        @NotEmpty(message = "내용을 입력하셔야 합니다.")
        private String content;

        private String name;

        public void setQuestionId(long questionId){
            this.questionId = questionId;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class response{
        private long questionId;
        private String title;
        private String content;
        private LocalDateTime date;
        private long votes;
        private Member member;
    }
}
