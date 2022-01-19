package com.kakao.cafe.exception;

import com.kakao.cafe.exception.article.ArticleNotFoundException;
import com.kakao.cafe.exception.user.DuplicateUserIdException;
import com.kakao.cafe.exception.user.IncorrectPasswordException;
import com.kakao.cafe.exception.user.NotAllowedUserException;
import com.kakao.cafe.exception.user.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CafeExceptionHandler {

    Logger logger = LoggerFactory.getLogger(CafeExceptionHandler.class);

    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String BadRequestException(CustomException e, Model model) {
        model.addAttribute("errorStatus", e.getErrorCode().getHttpStatus());
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

    @ExceptionHandler(NotAllowedUserException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String ForbiddenException(CustomException e, Model model) {
        model.addAttribute("errorStatus", e.getErrorCode().getHttpStatus());
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

    @ExceptionHandler({UserNotFoundException.class, ArticleNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String NotFoundException(CustomException e, Model model) {
        model.addAttribute("errorStatus", e.getErrorCode().getHttpStatus());
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

    @ExceptionHandler(DuplicateUserIdException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String ConflictException(CustomException e, Model model) {
        model.addAttribute("errorStatus", e.getErrorCode().getHttpStatus());
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
}
