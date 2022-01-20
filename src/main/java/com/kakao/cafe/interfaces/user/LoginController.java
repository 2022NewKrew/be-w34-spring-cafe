package com.kakao.cafe.interfaces.user;

import com.kakao.cafe.application.user.FindUserService;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.interfaces.user.dto.request.LoginRequestDto;
import com.kakao.cafe.interfaces.user.dto.response.LoginInfoDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class LoginController {
    private final FindUserService findUserService;

    public LoginController(FindUserService findUserService) {
        this.findUserService = findUserService;
    }

    @GetMapping("/login")
    public ModelAndView loginPage(ModelAndView modelAndView) {
        modelAndView.setViewName("/user/login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid LoginRequestDto loginRequestDto, HttpServletRequest request, ModelAndView modelAndView) {
        boolean passwordMatch = findUserService.checkPassWordMatch(loginRequestDto.getUserId(), loginRequestDto.getPassword());

        if (!passwordMatch) {
            request.getSession().invalidate();
            modelAndView.setViewName("user/login_failed");
            return modelAndView;
        }

        User user = findUserService.findByUserId(loginRequestDto.getUserId());
        LoginInfoDto loginInfoDto = new LoginInfoDto(user);
        request.getSession().setAttribute("sessionedUser", loginInfoDto);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session, ModelAndView modelAndView) {
        session.removeAttribute("sessionedUser");
        modelAndView.setViewName("redirect:/");

        return modelAndView;
    }
}
