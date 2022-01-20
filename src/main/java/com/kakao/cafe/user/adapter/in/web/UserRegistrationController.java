package com.kakao.cafe.user.adapter.in.web;

import com.kakao.cafe.common.meta.URLPath;
import com.kakao.cafe.user.application.port.in.UserRegistrationCommand;
import com.kakao.cafe.user.application.port.in.UserSignUpUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {
    private final UserSignUpUseCase userSignUpUseCase;

    @Autowired
    public UserRegistrationController(UserSignUpUseCase userSignUpUseCase) {
        this.userSignUpUseCase = userSignUpUseCase;
    }

    @GetMapping("/user/form")
    public String userRegisterForm() {
        return "user/form";
    }

    @PostMapping("/users")
    public String registerUser(@Valid @ModelAttribute UserRegistrationCommand userRegistrationCommand, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "redirect:user/form";
        userSignUpUseCase.saveUser(userRegistrationCommand);
        return URLPath.INDEX.getRedirectPath();
    }
}
