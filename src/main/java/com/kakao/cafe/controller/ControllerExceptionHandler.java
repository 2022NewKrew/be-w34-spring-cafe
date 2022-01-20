package com.kakao.cafe.controller;

import com.kakao.cafe.exception.ArticleNotFoundException;
import com.kakao.cafe.exception.NoAuthorizationException;
import com.kakao.cafe.exception.NoLoginException;
import com.kakao.cafe.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public String HttpClientErrorException(HttpClientErrorException e, Model model) {
        if(e.getStatusCode() == HttpStatus.FORBIDDEN){
            model.addAttribute("errorTitle", "허용되지 않은 접근입니다.");
            model.addAttribute("errorMessage", e.getMessage());
        }
        if(e.getStatusCode() == HttpStatus.UNAUTHORIZED){
            model.addAttribute("errorTitle", "로그인 후 이용할 수 있습니다.");
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "error";
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public String articleNotFoundException(Exception e, Model model) {
        model.addAttribute("errorTitle", "게시글이 삭제되었거나 찾을 수 없습니다.");
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String userNotFoundException(UserNotFoundException e, Model model) {
        model.addAttribute("errorTitle", "해당 회원이 탈퇴하였거나 찾을 수 없습니다.");
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String otherException(Exception e, Model model){
        model.addAttribute("errorTitle", "알 수 없는 에러가 발생하였습니다.");
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
}
