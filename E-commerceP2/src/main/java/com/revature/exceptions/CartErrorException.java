package com.revature.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CartErrorException extends RuntimeException {
    public CartErrorException(String message) {
        super(message);
    }
}
