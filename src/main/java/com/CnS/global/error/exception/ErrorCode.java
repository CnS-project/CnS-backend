package com.CnS.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    // Base
    INVALID_INPUT_VALUE(400, "B4001", " Invalid Input Value"),
    INVALID_TYPE_VALUE(400, "B4002", " Invalid Type Value"),
    ENTITY_NOT_FOUND(400, "B4003", " Entity Not Found"),
    HANDLE_ACCESS_DENIED(403, "B4031", "Access is Denied"),
    METHOD_NOT_ALLOWED(405, "B4051", " Invalid Input Value"),
    INTERNAL_SERVER_ERROR(500, "B5001", "Server Error"),

    // Course
    DUPLICATE_COURSE(400, "C4001", "The course is existed.")

    ;
    private final String code;
    private final String message;
    private final int status;


    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;

    }

}
