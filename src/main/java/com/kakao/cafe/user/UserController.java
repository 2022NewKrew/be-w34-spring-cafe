package com.kakao.cafe.user;

import com.kakao.cafe.user.domain.Password;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserId;
import com.kakao.cafe.user.dto.LoginFormDto;
import com.kakao.cafe.user.dto.UserFormDto;
import com.kakao.cafe.user.dto.UserMapper;
import com.kakao.cafe.user.dto.UserProfileDto;
import com.kakao.cafe.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/form")
    public String showUserForm() {
        return "user/form";
    }

    @GetMapping("/user/{userId}/form")
    public String showUpdateUserForm(@PathVariable String userId, Model model, HttpSession session) {
        Optional<UserId> loginId = Optional.ofNullable((UserId) session.getAttribute("loginUser"));
        User user = userService.findLoginUser(new UserId(userId), loginId);
        model.addAttribute("user", UserMapper.toUserDto(user));
        return "user/updateForm";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute UserFormDto userFormDto) {
        User user = UserMapper.toUser(userFormDto);
        userService.joinUser(user);
        return "redirect:/users";
    }

    @PutMapping("/user/update")
    public String updateUser(@ModelAttribute UserFormDto userFormDto) {
        User user = UserMapper.toUser(userFormDto);
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String findUsers(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("users", UserMapper.toListUserDto(users));
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String findUserProfile(@PathVariable String userId, Model model) {
        User user = userService.findUserByUserId(new UserId(userId));
        UserProfileDto userProfileDto = UserMapper.toUserProfileDto(user);
        model.addAttribute("user", userProfileDto);
        return "user/profile";
    }

    @GetMapping("user/login")
    public String showLoginForm() {
        return "/user/login";
    }

    @GetMapping("user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("user/login")
    public String login(@ModelAttribute LoginFormDto loginFormDto, HttpSession session) {
        UserId userId = new UserId(loginFormDto.getUserId());
        Password password = new Password(loginFormDto.getPassword());
        User user = userService.login(userId, password);
        session.setAttribute("loginUser", user.getUserId());
        return "redirect:/";
    }
}