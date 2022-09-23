package com.revature.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PaymentErrorException extends RuntimeException {
    public PaymentErrorException(String message) {
        super(message);
    }
}
