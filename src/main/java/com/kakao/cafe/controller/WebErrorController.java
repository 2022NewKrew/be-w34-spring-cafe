package com.kakao.cafe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@Controller
@Slf4j
public class WebErrorController implements ErrorController {
    @ExceptionHandler({IllegalArgumentException.class})
    public String argsExceptionHandle(Exception e, Model model){
        log.error("argsExceptionHandle - {}", e.getMessage());
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

    @ExceptionHandler({Exception.class})
    public String globalExceptionHandle(Exception e, Model model){
        log.error("globalExceptionHandle - {}", e.getMessage());
        model.addAttribute("errorMessage", "서버 에러");
        return "error";
    }

    @RequestMapping("/error")
    public String errorPage(Model model){
        model.addAttribute("errorMessage", "페이지를 찾을 수 없습니다.");
        return "error";
    }

    public String getErrorPath(){
        return "/error";
    }
}
