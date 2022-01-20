package com.kakao.cafe.user.adapter.in;

import com.kakao.cafe.user.application.port.in.FoundUserDto;
import com.kakao.cafe.user.application.port.in.LoginUseCase;
import com.kakao.cafe.user.domain.Email;
import com.kakao.cafe.user.domain.Password;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginUseCase loginUseCase;

    public LoginController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @GetMapping()
    public String loginForm() {
        return "/user/login_form";
    }

    @PostMapping()
    public String login(String email, String password, HttpSession session) {
        FoundUserDto foundUserDto = loginUseCase.login(new Email(email), new Password(password));
        ViewUserDto viewUserDto = foundUserDto.toViewUserDto();
        session.setAttribute("sessionedUser", viewUserDto);
        return "redirect:/";
    }
}
