package com.kakao.cafe.infra.exception;

import com.kakao.cafe.module.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ExceptionHandler({
            DuplicateNameException.class,
            NoSuchDataException.class
    })
    public String handleRuntimeExceptions(final CustomRuntimeException e, Model model) {
        model.addAttribute("msg", errorMsg(e.getName(), e.getMessage()));
        logger.error(e.getName());
        return "infra/error";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(final IllegalArgumentException e, Model model) {
        model.addAttribute("msg", errorMsg("IllegalArgumentException", e.getMessage()));
        logger.error("IllegalArgumentException");
        return "infra/error";
    }

    private String errorMsg(String name, String msg) {
        return name + " : " + msg;
    }
}
