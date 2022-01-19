package com.kakao.cafe.controller.error;

import lombok.Getter;
import org.springframework.ui.Model;

@Getter
public class ErrorMessageBox {
    private String message;

    public ErrorMessageBox(String message) {
        this.message = message;
    }

    public static String handling(String msg, Model model) {
        model.addAttribute("error", new ErrorMessageBox(msg));
        return "/error";
    }
}
