package com.kakao.cafe.error.handler;

import com.kakao.cafe.error.ErrorDetail;
import com.kakao.cafe.error.exception.ArticleNotFoundException;
import com.kakao.cafe.error.exception.AuthInvalidPasswordException;
import com.kakao.cafe.error.exception.AuthInvalidUidException;
import com.kakao.cafe.error.exception.BindingException;
import com.kakao.cafe.error.exception.UserAlreadyExistsException;
import com.kakao.cafe.error.exception.UserInvalidAuthInfoException;
import com.kakao.cafe.error.exception.UserNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleUserNotFoundException(UserNotFoundException e) {
        logger.error(e.getMessage());

        ModelAndView modelAndView = new ModelAndView("/error");
        ErrorDetail errorDetail = ErrorDetail.from(e.getMessage());
        modelAndView.getModelMap().addAttribute("detail", errorDetail);
        return modelAndView;
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ModelAndView handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        logger.error(e.getMessage());

        ModelAndView modelAndView = new ModelAndView("/error");
        ErrorDetail errorDetail = ErrorDetail.from(e.getMessage());
        modelAndView.getModelMap().addAttribute("detail", errorDetail);
        return modelAndView;
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ModelAndView handleArticleNotFoundException(ArticleNotFoundException e) {
        logger.error(e.getMessage());

        ModelAndView modelAndView = new ModelAndView("/error");
        ErrorDetail errorDetail = ErrorDetail.from(e.getMessage());
        modelAndView.getModelMap().addAttribute("detail", errorDetail);
        return modelAndView;
    }

    @ExceptionHandler({AuthInvalidPasswordException.class, AuthInvalidUidException.class})
    public ModelAndView handleAuthInvalidException(RuntimeException e) {
        logger.error(e.getMessage());

        ModelAndView modelAndView = new ModelAndView("/error");
        ErrorDetail errorDetail = ErrorDetail.from(e.getMessage());
        modelAndView.getModelMap().addAttribute("detail", errorDetail);
        return modelAndView;
    }

    @ExceptionHandler(UserInvalidAuthInfoException.class)
    public ModelAndView handleUserInvalidAuthInfoException(UserInvalidAuthInfoException e) {
        logger.error(e.getMessage());

        ModelAndView modelAndView = new ModelAndView("/error");
        ErrorDetail errorDetail = ErrorDetail.from(e.getMessage());
        modelAndView.getModelMap().addAttribute("detail", errorDetail);
        return modelAndView;
    }

    @ExceptionHandler(BindingException.class)
    public ModelAndView handleBindingException(BindingException e) {
        logger.error(e.getMessage());

        ModelAndView modelAndView = new ModelAndView("/error");
        List<ErrorDetail> errorDetails = Arrays.stream(e.getMessage().split(System.lineSeparator()))
            .map(ErrorDetail::from)
            .collect(Collectors.toList());
        modelAndView.getModelMap().addAttribute("detail", errorDetails);
        return modelAndView;
    }
}
