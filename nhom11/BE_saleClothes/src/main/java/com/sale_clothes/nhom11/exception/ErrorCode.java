package com.sale_clothes.nhom11.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001,"Username không tồn tại!"),
    PASSWORD_FAILED(1002,"Mật khẩu không chính xác"),
    UNAUTHENTICATED(1002,"Unauthenticated")


    ;
    private int code;
    private String message;
}
