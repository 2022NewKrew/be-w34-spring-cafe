package com.kakao.cafe.controller;

import com.kakao.cafe.dto.AuthInfoDTO.Login;
import com.kakao.cafe.persistence.model.AuthInfo;
import com.kakao.cafe.service.AuthService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    @PostMapping
    public String login(@ModelAttribute @Validated Login loginDTO, HttpServletRequest request,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors()
                .forEach(fieldError -> logger.error("Caused Field : {}, Message : {}",
                    fieldError.getField(),
                    fieldError.getDefaultMessage()));
            return "redirect:/login-failed";
        }
        AuthInfo authInfo = authService.login(loginDTO);

        HttpSession session = request.getSession();
        session.setAttribute("auth", authInfo);

        return "redirect:/";
    }

    @DeleteMapping
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }
}
