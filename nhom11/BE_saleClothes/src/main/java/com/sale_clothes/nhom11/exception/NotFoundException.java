package com.sale_clothes.nhom11.exception;

import lombok.NoArgsConstructor;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }

}
