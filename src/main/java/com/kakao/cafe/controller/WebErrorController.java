package com.kakao.cafe.controller;

import com.kakao.cafe.util.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Controller
@Slf4j
public class WebErrorController implements ErrorController {
    @ExceptionHandler({IllegalArgumentException.class, CustomException.class})
    public String argsExceptionHandle(Exception e, Model model, HttpServletResponse response){
        String exceptionName = e.getClass().getSimpleName();
        log.error("{} - {}", exceptionName, e.getMessage());
        model.addAttribute("errorMessage", e.getMessage());

        int status = parseStatusCode(exceptionName);

        if(status == HttpServletResponse.SC_INTERNAL_SERVER_ERROR){
            return "/error";
        }

        response.setStatus(status);
        return "error";
    }

    @ExceptionHandler({BindException.class})
    public String validateException(Errors errors, RedirectAttributes attr, HttpServletRequest request){
        errors.getFieldErrors().forEach(err -> attr.addFlashAttribute(err.getField(), err.getDefaultMessage()));
        return "redirect:" + request.getHeader("Referer");

    }

    private int parseStatusCode(String exception){
        switch (exception){
            case "IllegalArgumentException":
                return HttpServletResponse.SC_BAD_REQUEST;
            case "NotFoundException":
                return HttpServletResponse.SC_NOT_FOUND;
            case "UnAuthorizedException":
                return HttpServletResponse.SC_UNAUTHORIZED;
            default:
                return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        }
    }


    @RequestMapping("/error")
    public String errorPage(Model model, Exception e){
        log.error("{} - {}", e.getClass().getSimpleName(), e.getMessage());
        model.addAttribute("errorMessage", "페이지를 찾을 수 없습니다.");
        return "error";
    }

    public String getErrorPath(){
        return "/error";
    }
}
