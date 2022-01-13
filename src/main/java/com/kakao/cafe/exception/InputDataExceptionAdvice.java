package com.kakao.cafe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InputDataExceptionAdvice {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(InputDataException.class)
    public String inputError(Model model, InputDataException e){
        logger.info("에러 : {}",e.getMessage());
        model.addAttribute("msg",e.getMessage());
        return "./error/alert";
    }
}
