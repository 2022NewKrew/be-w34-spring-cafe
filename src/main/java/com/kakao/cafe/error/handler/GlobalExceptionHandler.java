package com.kakao.cafe.error.handler;

import com.kakao.cafe.error.ErrorDetail;
import com.kakao.cafe.error.exception.ArticleNotFoundException;
import com.kakao.cafe.error.exception.AuthInvalidPasswordException;
import com.kakao.cafe.error.exception.AuthInvalidUidException;
import com.kakao.cafe.error.exception.BindingException;
import com.kakao.cafe.error.exception.ForbiddenAccessException;
import com.kakao.cafe.error.exception.ReplyNotFoundException;
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

    @ExceptionHandler({UserNotFoundException.class,
        UserAlreadyExistsException.class,
        ArticleNotFoundException.class,
        AuthInvalidUidException.class,
        AuthInvalidPasswordException.class,
        UserInvalidAuthInfoException.class,
        ForbiddenAccessException.class,
        ReplyNotFoundException.class})
    public ModelAndView handleGlobalException(RuntimeException e) {
        logger.error("{} {}", e.getClass().getName(), e.getMessage());

        ModelAndView modelAndView = new ModelAndView("redirect:/error");
        ErrorDetail errorDetail = ErrorDetail.from(e.getMessage());
        modelAndView.getModelMap().addAttribute("detail", errorDetail);
        return modelAndView;
    }

    @ExceptionHandler(BindingException.class)
    public ModelAndView handleBindingException(BindingException e) {
        logger.error("{} {}", e.getClass().getName(), e.getMessage());

        ModelAndView modelAndView = new ModelAndView("redirect:/error");
        List<ErrorDetail> errorDetails = Arrays.stream(e.getMessage().split(System.lineSeparator()))
            .map(ErrorDetail::from)
            .collect(Collectors.toList());
        modelAndView.getModelMap().addAttribute("detail", errorDetails);
        return modelAndView;
    }
}
