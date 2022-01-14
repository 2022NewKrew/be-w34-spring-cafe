package com.kakao.cafe.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class KakaoCafeExceptionHandler {

    @ExceptionHandler({IllegalStateException.class, NoSuchElementException.class})
    public Object redirectToIndexException(Exception e){
        log.info(e.getMessage());
        e.printStackTrace();
        return "redirect:/";
    }
}
