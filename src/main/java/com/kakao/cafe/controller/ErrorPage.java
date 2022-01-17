package com.kakao.cafe.controller;

import org.springframework.ui.Model;

public class ErrorPage {
    public static String getErrorPageModel(Model model, String message) {

        model.addAttribute("message", message);
        return "error/base_error";
    }
}
