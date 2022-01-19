package com.kakao.cafe.user.adapter.in;

import com.kakao.cafe.user.application.port.in.SignUpUseCase;
import com.kakao.cafe.user.application.port.in.SignUpUserDto;
import com.kakao.cafe.user.domain.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    private final SignUpUseCase signUpUseCase;
    private final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    public SignUpController(SignUpUseCase signUpUseCase) {
        this.signUpUseCase = signUpUseCase;
    }

    @GetMapping("/user/form")
    public String routeSignUpForm() {
        return "/user/form";
    }

    @PostMapping("/users")
    public String signUp(@ModelAttribute SignUpRequestDto signUpRequestDto) {
        SignUpUserDto signUpUserDto = new SignUpUserDto(
            signUpRequestDto.getEmail(),
            signUpRequestDto.getNickname(),
            signUpRequestDto.getPassword());

        UserId createdUserId = signUpUseCase.signUp(signUpUserDto);
        logger.info("[Log] 유저가 생성되었습니다. {}", createdUserId.getId());
        return "redirect:/users";
    }

}
