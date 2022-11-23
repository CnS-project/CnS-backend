package com.CnS.global.error.exception;

import lombok.Getter;

@Getter
public class ServerException extends RuntimeException{

    private final ErrorCode errorCode;

    public ServerException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServerException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }


}
