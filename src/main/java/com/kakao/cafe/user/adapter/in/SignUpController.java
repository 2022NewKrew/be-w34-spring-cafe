package com.kakao.cafe.user.adapter.in;

import com.kakao.cafe.user.application.port.in.SignUpUseCase;
import com.kakao.cafe.user.application.port.in.SignUpUserDto;
import com.kakao.cafe.user.domain.Email;
import com.kakao.cafe.user.domain.Password;
import com.kakao.cafe.user.domain.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class SignUpController {

    private final SignUpUseCase signUpUseCase;
    private final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    public SignUpController(SignUpUseCase signUpUseCase) {
        this.signUpUseCase = signUpUseCase;
    }

    @GetMapping("/form")
    public String routeSignUpForm() {
        return "user/sign_up_form";
    }

    @PostMapping()
    public String signUp(@ModelAttribute SignUpRequestDto signUpRequestDto) {
        System.out.printf("%s %s %s \n", signUpRequestDto.getEmail(),
            signUpRequestDto.getNickname(),
            signUpRequestDto.getPassword());
        SignUpUserDto signUpUserDto = new SignUpUserDto(
            new Email(signUpRequestDto.getEmail()),
            signUpRequestDto.getNickname(),
            new Password(signUpRequestDto.getPassword()));

        UserId createdUserId = signUpUseCase.signUp(signUpUserDto);
        logger.info("[Log] 유저가 생성되었습니다. {}", createdUserId.getId());
        return "redirect:/users";
    }
}
