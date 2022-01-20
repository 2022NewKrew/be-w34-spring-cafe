package com.kakao.cafe.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView databaseError(DataAccessException e) {
        logger.info("databaseError: {}", e.getMessage());
        return new ModelAndView("error", "error", "서버 오류");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView invalidUserProfileError(InvalidUserProfileException e) {
        logger.info("invalidUserProfileError");
        return new ModelAndView("error", "error", "NOT FOUND");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ModelAndView invalidUserIdError(InvalidUserIdException e) {
        logger.info("invalidUserIdError");
        return new ModelAndView("error", "error", "잘못된 유저 아이디");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ModelAndView duplicateUserIdError(DuplicateUserIdException e) {
        logger.info("duplicateUserIdError");
        return new ModelAndView("error", "error", "중복된 아이디");
    }

    @ExceptionHandler
    public String noUserAndPasswordError(NoUserAndPasswordException e) {
        logger.info("noUserAndPasswordError");
        return "redirect:/login_failed";
    }

    @ExceptionHandler
    public String loginRequiredError(LoginRequiredException e) {
        logger.info("loginRequiredError");
        return "redirect:/login";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView noPermissionError(NoPermissionException e) {
        logger.info("noPermissionError");
        return new ModelAndView("error", "error", "수정 권한 없음");
    }
}
