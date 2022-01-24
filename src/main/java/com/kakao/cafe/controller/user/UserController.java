package com.kakao.cafe.controller.user;

import com.kakao.cafe.service.user.UserService;
import com.kakao.cafe.service.user.dto.UserElementDto;
import com.kakao.cafe.service.user.dto.UserInformationDto;
import com.kakao.cafe.service.user.dto.UserLoginDto;
import com.kakao.cafe.service.user.dto.UserRegisterDto;
import com.kakao.cafe.service.user.dto.UserUpdateDto;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String startPage() {
        return "redirect:/index/1";
    }

    @GetMapping("/users/form")
    public String registerForm() {
        return "user/form";
    }

    @GetMapping("/login/form")
    public String loginForm() {
        return "user/login";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<UserElementDto> userElementDtos = userService.getUsers();
        model.addAttribute("users", userElementDtos);
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String showUserProfile(@PathVariable String userId, Model model) {
        UserInformationDto userInformationDto = userService.findUserByUserId(userId);
        model.addAttribute("user", userInformationDto);
        return "user/profile";
    }

    @PostMapping("/login")
    public String login(UserLoginDto userLoginDto, HttpSession session) {
        userService.login(userLoginDto);
        session.setAttribute("loginUserId", userLoginDto.getUserId());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/users")
    public String register(UserRegisterDto userRegisterDto) {
        userService.createUser(userRegisterDto);
        return "redirect:/users";
    }

    @GetMapping("/users/update")
    public String showUpdateUserInformation(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("loginUserId");
        UserInformationDto userInformationDto = userService.findUserByUserId(userId);
        model.addAttribute("user", userInformationDto);
        return "user/updateForm";
    }

    @PostMapping("/users/update")
    public String updateUserInformation(UserUpdateDto userUpdateDto, HttpSession session) {
        userService.updateUser(userUpdateDto);
        return "redirect:/";
    }
}
