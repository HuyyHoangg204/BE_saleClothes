package com.sale_clothes.nhom11.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001,"User is existed"),
    UNAUTHENTICATED(1002,"Unauthenticated")


    ;
    private int code;
    private String message;
}
