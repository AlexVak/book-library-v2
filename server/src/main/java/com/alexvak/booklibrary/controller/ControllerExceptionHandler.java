package com.alexvak.booklibrary.controller;

import com.alexvak.booklibrary.exception.BookNotFoundException;
import com.alexvak.booklibrary.payload.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiResponse> handleBookNotFoundException(Exception exception) {
        return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ApiResponse> handleBadRequest(Exception exception) {
        return new ResponseEntity<>(new ApiResponse(false, "Incorrect number format: " + exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
