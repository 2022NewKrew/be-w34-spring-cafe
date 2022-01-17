package com.example.kakaocafe.controller;

import com.example.kakaocafe.core.meta.ViewPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class SignUpController {

    @GetMapping(path = "sign-up")
    public ModelAndView showSignUpForm(Model model) {
        return new ModelAndView(ViewPath.SIGN_UP)
                .addAllObjects(model.asMap());
    }

    @GetMapping(path = "sign-up-success")
    public ModelAndView showSignUpSuccess(Model model) {
        return new ModelAndView(ViewPath.SIGN_UP_SUCCESS)
                .addAllObjects(model.asMap());
    }
}
