package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.exception.ArticleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = ArticleController.class)
public class ArticleExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleExceptionController.class);

    @ExceptionHandler(value = ArticleException.class)
    public String handleException(ArticleException e, Model model) {
        logger.warn(e.getMessage());
        model.addAttribute("errorMsg", e.getMessage());
        return "error";
    }
}
