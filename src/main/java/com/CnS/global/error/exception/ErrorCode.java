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
    DUPLICATE_COURSE(400, "C4001", "The course is existed"),
    COURSE_NOT_EXIST(400, "C4002", "The course isn't exist"),
    OVER_CAPACITY(400, "C4003", "The capacity is full"),

    // User
    DUPLICATE_REGISTER(400, "U4001", "This course has already been registered.\n"),
    NONE_SESSION_INFORMATION(401, "U4011", "Session information does not exist"),
    INVALID_USER_ID(401, "U4012", "User id is invalid"),
    INVALID_USER_PASSWORD(401, "U4013", "password is invalid"),
    OVER_CREDIT(400, "U4002", "Over Credit"),


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
