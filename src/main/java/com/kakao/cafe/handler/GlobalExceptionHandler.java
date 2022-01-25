package com.kakao.cafe.handler;

import com.kakao.cafe.controller.PostController;
import com.kakao.cafe.exceptions.DuplicateUserException;
import com.kakao.cafe.exceptions.InvalidLoginRequestException;
import com.kakao.cafe.exceptions.InvalidUserRequestException;
import com.kakao.cafe.exceptions.InvalidWritePostException;
import com.kakao.cafe.exceptions.PostNotFoundException;
import com.kakao.cafe.exceptions.UnauthenticatedPostAccessException;
import com.kakao.cafe.exceptions.UserNotFoundException;
import com.kakao.cafe.exceptions.WrongPasswordException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_VIEW_NAME = "errors/error";
    private final ModelAndView mv = new ModelAndView(ERROR_VIEW_NAME);
    private final Logger logger = LoggerFactory.getLogger(PostController.class);

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView userNotFound(UserNotFoundException exception) {
        return errorModelAndView(exception);
    }

    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ModelAndView duplicateUser(DuplicateUserException exception) {
        return errorModelAndView(exception);
    }

    @ExceptionHandler(InvalidUserRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView userValidationFailed(InvalidUserRequestException exception) {
        return errorModelAndView(exception);
    }

    @ExceptionHandler(InvalidWritePostException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView postValidationFailed(InvalidWritePostException exception) {
        return errorModelAndView(exception);
    }

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView postNotFound(PostNotFoundException exception) {
        return errorModelAndView(exception);
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView postNotFound(WrongPasswordException exception) {
        return errorModelAndView(exception);
    }

    @ExceptionHandler(InvalidLoginRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView loginValidationFailed(InvalidLoginRequestException exception) {
        return errorModelAndView(exception);
    }

    @ExceptionHandler(UnauthenticatedPostAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView unauthenticatedArticleAccess(UnauthenticatedPostAccessException exception) {
        return errorModelAndView(exception);
    }

    private void logHandlingException(RuntimeException e) {
        logger.info("Exception Handled: class={}, message={}",
                e.getClass().getName(),
                e.getMessage());
    }

    private ModelAndView errorModelAndView(RuntimeException exception) {
        logHandlingException(exception);
        Map<String, Object> model = mv.getModel();
        model.put("message", exception.getMessage());
        return mv;
    }
}
