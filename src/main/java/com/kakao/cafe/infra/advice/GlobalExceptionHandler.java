package com.kakao.cafe.infra.advice;

import com.kakao.cafe.common.dto.ErrorResponse;
import com.kakao.cafe.common.exception.NotFoundException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_VIEW_NAME = "errors/error";
    private final ModelAndView mv = new ModelAndView(ERROR_VIEW_NAME);

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFoundException(NotFoundException e) {
        log.error("[NotFoundException] - {}", e.getMessage());
        Map<String, Object> model = mv.getModel();
        model.put("message", ErrorResponse.of(e.getMessage()));
        return mv;
    }

    @ExceptionHandler(BindException.class)
    public ModelAndView bindException(BindException e) {
        log.error("[BindException] - {}", e.getMessage());
        Map<String, Object> model = mv.getModel();
        model.put("message", ErrorResponse.of(e.getBindingResult()));
        return mv;
    }
}
