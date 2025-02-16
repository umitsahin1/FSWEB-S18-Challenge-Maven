package com.workintech.fswebs18challengemaven.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CardException extends RuntimeException {
    private HttpStatus status;

    public CardException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getHttpStatus() {
        return status;
    }
}