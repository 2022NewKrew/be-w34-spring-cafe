package com.kakao.cafe.advice;

import com.kakao.cafe.dto.ErrorResponse;
import com.kakao.cafe.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

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

    @ExceptionHandler(QnaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleQnaNotFoundException(QnaNotFoundException qnaNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse(404, qnaNotFoundException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({LoginUserNotFoundException.class, IncorrectLoginPasswordException.class})
    public void handleLoginException(Exception exception, HttpServletResponse response) throws IOException {
        System.out.println(exception.getMessage());
        response.sendRedirect("/auth/login/failform");
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCommentNotFoundException(CommentNotFoundException commentNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse(404, commentNotFoundException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException accessDeniedException) {
        ErrorResponse errorResponse = new ErrorResponse(403, accessDeniedException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({AlreadyDeletedCommentException.class, AlreadyDeletedQnaException.class})
    public ResponseEntity<ErrorResponse> handleAlreadyDeletedException(RuntimeException runtimeException) {
        ErrorResponse errorResponse = new ErrorResponse(409, runtimeException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
