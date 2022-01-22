package com.kakao.cafe.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class Utils {

    public static boolean isValidBindingResult(BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            ObjectError error = bindingResult.getAllErrors().get(0);
            model.addAttribute("error", error.getDefaultMessage());
            return false;
        }
        return true;
    }
}
