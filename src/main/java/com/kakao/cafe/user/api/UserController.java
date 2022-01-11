package com.kakao.cafe.user.api;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.SignUpReq;
import com.kakao.cafe.user.exception.UserIdDuplicateException;
import com.kakao.cafe.user.exception.UserNotFoundException;
import com.kakao.cafe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getUserList(Model model) {
        List<User> users = userService.getUserList();
        model.addAttribute("users", users);
        model.addAttribute("size", users.size());
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String getUserByUserId(@PathVariable String userId, Model model) {
        User user = userService.findUserByUserId(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @PostMapping
    public String signUp(@Valid SignUpReq req) {
        userService.signUp(req);
        return "redirect:users/loginSuccess";
    }

    @ExceptionHandler(value = {UserNotFoundException.class, UserIdDuplicateException.class})
    public void userExceptionHandler() {

    }
}
