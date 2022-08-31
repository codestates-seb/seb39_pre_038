package com.pre_38.pre_project.exception;

import lombok.Getter;

public enum ExceptionCode {
    QUESTION_NOT_FOUND(404,"Question not found"),
    QUESTION_EXISTS(409, "Question exists"),
    QUESTION_CANNOT_CHANGE(403,"Question can not change"),
    REPLY_NOT_FOUND(404,"Reply not found"),
    REPLY_EXISTS(409, "Reply exists"),
    REPLY_CANNOT_CHANGE(403,"Reply can not change");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message){
        this.status = status;
        this.message = message;
    }
}
