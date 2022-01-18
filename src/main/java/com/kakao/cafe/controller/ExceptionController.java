package com.kakao.cafe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentException(Model model, IllegalArgumentException e) {
        log.info("ExceptionController : {}", e.getMessage());
        HashMap<String, Object> temp = new HashMap<>();
        temp.put("message", e.getMessage());
        model.addAllAttributes(temp);
        return "exception";
    }
}
