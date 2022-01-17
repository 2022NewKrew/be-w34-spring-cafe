package com.kakao.cafe.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerAdvisor {

    @ExceptionHandler(BaseException.class)
    public String handleBaseException(BaseException e, Model model) {
        return getErrorPageModel(model, e.getMessage());
    }

    public String getErrorPageModel(Model model, String message) {

        model.addAttribute("message", message);
        return "error/base_error";
    }

}
