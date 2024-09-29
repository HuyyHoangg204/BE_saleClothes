package com.sale_clothes.nhom11.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001,"Username không tồn tại!",HttpStatus.NOT_FOUND),
    PASSWORD_FAILED(1002,"Mật khẩu không chính xác",HttpStatus.UNAUTHORIZED),
    EMAIL_AlREADY_EXISTED(1003,"Email đã tồn tại",HttpStatus.CONFLICT),
    USER_ALREADY_EXISTED(1004,"Username đã tồn tại",HttpStatus.CONFLICT),
    UNAUTHENTICATED(1005,"Unauthenticated",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1006, "Bạn không có quyền truy cập",HttpStatus.FORBIDDEN)


    ;
    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
