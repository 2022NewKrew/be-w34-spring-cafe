package com.kakao.cafe.advice;

import com.kakao.cafe.dto.ErrorResponse;
import com.kakao.cafe.exception.AlreadyExistUserException;
import com.kakao.cafe.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistUserException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistUserException(AlreadyExistUserException alreadyExistUserException) {
        ErrorResponse errorResponse = new ErrorResponse(409, alreadyExistUserException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse(404, userNotFoundException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
