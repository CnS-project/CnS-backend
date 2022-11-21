package com.CnS.global.error.exception;

import lombok.Getter;

@Getter
public class CourseException extends RuntimeException{

    private final ErrorCode errorCode;

    public CourseException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CourseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
