package com.pre_38.pre_project.advice;

import com.pre_38.pre_project.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private int status;
    private String message;

    private ErrorResponse(int status, String message){
        this.status = status;
        this.message = message;
    }

    public static ErrorResponse of(ExceptionCode exceptionCode){
        return new ErrorResponse(exceptionCode.getStatus(),exceptionCode.getMessage());
    }
}
