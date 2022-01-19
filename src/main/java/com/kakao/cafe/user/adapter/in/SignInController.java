package com.kakao.cafe.user.adapter.in;

import com.kakao.cafe.user.application.port.in.FoundUserDto;
import com.kakao.cafe.user.application.port.in.SignInUseCase;
import com.kakao.cafe.user.domain.Password;
import com.kakao.cafe.user.domain.UserId;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;

public class SignInController {

    private final SignInUseCase signInUseCase;

    public SignInController(SignInUseCase signInUseCase) {
        this.signInUseCase = signInUseCase;
    }

    @PostMapping("/login")
    public String signIn(String userId, String password, HttpSession session) {
        FoundUserDto foundUserDto = signInUseCase.signIn(new UserId(userId),
            new Password(password));
        ViewUserDto viewUserDto = foundUserDto.toViewUserDto();
        session.setAttribute("sessioned", viewUserDto);
        return "redirect:/";
    }

}
