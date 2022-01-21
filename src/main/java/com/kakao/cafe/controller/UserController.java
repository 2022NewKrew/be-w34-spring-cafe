package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.SessionUserInfoCheck;
import com.kakao.cafe.dto.LoginDTO;
import com.kakao.cafe.dto.SessionUserDTO;
import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {
    @Resource(name = "userService")
    private UserService userService;

    @GetMapping
    String getUsers(Model model) {
        List<UserDTO> userList = userService.getUserList();
        model.addAttribute("users", userList);
        return "user/list";
    }

    @PostMapping("/create")
    String createUser(@Valid UserDTO user) {
        userService.insertUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/form")
    @SessionUserInfoCheck
    String getUserForm(@PathVariable Long id, Model model, SessionUserDTO sessionUser) {
        model.addAttribute("user", sessionUser);
        log.info("get User(Form) -> ID : {}, UserId : {}", id, sessionUser.getUserId());
        return "user/updateForm";
    }

    @PutMapping("/{id}/update")
    @SessionUserInfoCheck
    String updateUser(@PathVariable Long id, @Valid UserDTO user, SessionUserDTO sessionUser) {
        userService.updateUser(id, user, sessionUser.getPassword());
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    String getUserProfile(@PathVariable long id, Model model) {
        userService.getUserById(id, model);
        return "user/profile";
    }

    @PostMapping("/loginProcess")
    String loginProcess(@Valid LoginDTO login) {
        userService.loginByLoginData(login);
        return "redirect:/";
    }

    @GetMapping("/logout")
    String logout() {
        userService.logOut();
        return "redirect:/";
    }

}
