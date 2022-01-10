package com.kakao.cafe.adapter.in.view;

import com.kakao.cafe.application.user.dto.SignUpRequest;
import com.kakao.cafe.application.user.port.in.SignUpUserUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserSignUpController {

    private static final Logger log = LoggerFactory.getLogger(UserSignUpController.class);

    private final SignUpUserUseCase signUpUserUseCase;

    public UserSignUpController(
        SignUpUserUseCase signUpUserUseCase) {
        this.signUpUserUseCase = signUpUserUseCase;
    }

    @PostMapping("/users")
    public String signUp(SignUpRequest signUpRequest) {
        log.info("{} joined as a member", signUpRequest.getUserId());
        signUpUserUseCase.signUpUser(signUpRequest);
        return "redirect:/users";
    }
}
