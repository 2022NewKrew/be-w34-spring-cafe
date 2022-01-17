package com.kakao.cafe.controller.article.advice;

import com.kakao.cafe.controller.article.ArticleSaveController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = ArticleSaveController.class)
public class ArticleSaveControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException exception, Model model) {
        model.addAttribute("errorExist", true);
        model.addAttribute("errorMsg", exception.getMessage());
        return "qna/form";
    }
}
