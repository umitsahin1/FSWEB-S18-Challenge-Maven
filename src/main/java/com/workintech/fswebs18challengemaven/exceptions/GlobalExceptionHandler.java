package com.workintech.fswebs18challengemaven.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CardException.class)
    public ResponseEntity<CardErrorResponse> handleCardException(CardException ex) {
        CardErrorResponse response = new CardErrorResponse(ex.getMessage());
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CardErrorResponse> handleGeneralException(Exception ex) {
        CardErrorResponse response = new CardErrorResponse("Unexpected error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}