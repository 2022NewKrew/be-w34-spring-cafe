package com.kakao.cafe.adapter.in.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ErrorController {

    private static final String VIEW_ERROR_PAGE = "error";

    @GetMapping("/errors")
    public String showErrorPage(@RequestParam("message") String message, Model model) {
        model.addAttribute("message", message);
        return VIEW_ERROR_PAGE;
    }
}
