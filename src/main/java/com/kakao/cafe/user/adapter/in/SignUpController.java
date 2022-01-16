package com.kakao.cafe.user.adapter.in;

import com.kakao.cafe.user.application.port.in.CreateUserDto;
import com.kakao.cafe.user.application.port.in.SignUpUseCase;
import com.kakao.cafe.user.domain.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class SignUpController {

    private final SignUpUseCase signUpUseCase;
    private final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    public SignUpController(SignUpUseCase signUpUseCase) {
        this.signUpUseCase = signUpUseCase;
    }

    @GetMapping("user/form")
    public String routeSignUpForm() {
        return "user/form";
    }

    @PostMapping("/users")
    public String signUp(@ModelAttribute SignUpRequestDto signUpRequestDto) {
        CreateUserDto createUserDto = new CreateUserDto(
            signUpRequestDto.getEmail(),
            signUpRequestDto.getNickname(),
            signUpRequestDto.getPassword());

        UserId createdUserId = signUpUseCase.signUp(createUserDto);
        logger.info("[Log] 유저가 생성되었습니다. {}", createdUserId.getUUID());
        return "redirect:/users";
    }

}
