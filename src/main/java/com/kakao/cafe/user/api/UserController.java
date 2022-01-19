package com.kakao.cafe.user.api;

import com.kakao.cafe.user.dto.request.LoginReq;
import com.kakao.cafe.user.dto.request.SignUpReq;
import com.kakao.cafe.user.dto.request.UserUpdateReq;
import com.kakao.cafe.user.dto.response.UserDto;
import com.kakao.cafe.user.exception.UserIdDuplicateException;
import com.kakao.cafe.user.exception.UserNotFoundException;
import com.kakao.cafe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getUserList(Model model) {
        List<UserDto> users = userService.getUserList();
        model.addAttribute("users", users);
        model.addAttribute("size", users.size());
        return "user/list";
    }

    @GetMapping("/{id}")
    public String getUserProfile(@PathVariable Long id, Model model) {
        log.info("[GET] /users/{} - 유저 프로필 보기", id);
        UserDto user = userService.getProfileById(id);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @PostMapping
    public String signUp(@Valid SignUpReq req) {
        log.info("[POST] : /users - 회원가입 요청");
        userService.signUp(req);
        return "redirect:/users/loginSuccess";
    }

    @PostMapping("/login")
    public String login(@Valid LoginReq req, HttpSession session) {
        log.info("[POST] : /users/login - 로그인 요청");
        UserDto user = userService.getLoginUser(req);
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @PutMapping("/update")
    public String updateUser(@Valid UserUpdateReq req, HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("user");
        log.info("[PUT] : /users/update - {} 유저 정보 수정", user.getUserId());

        UserDto newUser = userService.updateUser(user.getId(), req);
        session.setAttribute("user", newUser);
        return "redirect:/";
    }

    @DeleteMapping("/logout")
    public String logout(HttpSession session) {
        log.info("[DELETE] : /users/logout - 로그아웃 요청");
        session.invalidate();
        return "redirect:/";
    }

    @ExceptionHandler(value = {UserNotFoundException.class, UserIdDuplicateException.class})
    public void userExceptionHandler() {

    }
}
