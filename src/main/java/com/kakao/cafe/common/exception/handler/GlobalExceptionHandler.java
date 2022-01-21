package com.kakao.cafe.common.exception.handler;

import com.kakao.cafe.common.exception.BusinessException;
import com.kakao.cafe.common.exception.ErrorCode;
import com.kakao.cafe.common.exception.dto.ErrorResponse;
import com.kakao.cafe.user.controller.UserController;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ExceptionHandler(BindException.class)
    protected ModelAndView handleBindException(BindException e, HttpServletRequest request,
        RedirectAttributes redirect) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        logger.warn("{}: {}", e.getMessage(), ErrorCode.INVALID_INPUT_VALUE.getMessage());

        String referer = request.getHeader("referer");
        ModelAndView mv = new ModelAndView("redirect:" + referer);
        redirect.addFlashAttribute("e", errorResponse);
        return mv;
    }

    @ExceptionHandler(BusinessException.class)
    protected ModelAndView handleBusinessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);
        logger.warn(e.getErrorCode().getMessage());

        ModelAndView mv = new ModelAndView("exception");
        mv.addObject("e",errorResponse);
        mv.addObject("status", HttpStatus.valueOf(errorCode.getStatus()));
        return mv;
    }
}
