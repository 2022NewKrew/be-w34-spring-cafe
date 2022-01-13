package com.kakao.cafe.adapter.in.presentation.user;

import com.kakao.cafe.application.user.dto.SignUpRequest;
import com.kakao.cafe.application.user.port.in.SignUpUserUseCase;
import com.kakao.cafe.view.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserSignUpController {

    private static final Logger log = LoggerFactory.getLogger(UserSignUpController.class);

    private final SignUpUserUseCase signUpUserUseCase;

    public UserSignUpController(SignUpUserUseCase signUpUserUseCase) {
        this.signUpUserUseCase = signUpUserUseCase;
    }

    @PostMapping("/users")
    public String signUp(SignUpRequest signUpRequest, RedirectAttributes redirectAttributes) {
        try {
            signUpUserUseCase.signUpUser(signUpRequest);
            log.info("{} joined as a member", signUpRequest.getUserId());
            return "redirect:/users";
        } catch (Exception e) {
            log.info("{}", e.getMessage());
            String message = ErrorMessage.getErrorMessage(e);
            redirectAttributes.addAttribute("message", message);
            return "redirect:/errors";
        }
    }
}
