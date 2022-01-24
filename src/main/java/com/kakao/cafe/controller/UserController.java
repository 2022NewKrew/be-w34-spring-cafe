package com.kakao.cafe.controller;

import com.kakao.cafe.dto.user.UserCreationDto;
import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.util.SessionIdRequired;
import com.kakao.cafe.util.Url;
import com.kakao.cafe.util.View;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUserList(Model model) {
        var userList = userService.findAllUsers();
        model.addAttribute("userListSize", userList.size());
        model.addAttribute("userList", userList);
        return View.USER_LIST;
    }

    @PostMapping("/users")
    public String createUser(@Valid UserCreationDto userCreationDTO,
                             Errors errors) {
        validateParams(errors);
        userService.join(userCreationDTO);
        return "redirect:" + Url.USERS;
    }

    @SessionIdRequired
    @GetMapping("/users/{id}")
    public String getUserProfile(@PathVariable("id") Long id,
                                 Model model) {
        model.addAttribute("user", getUserById(id));
        return View.USER_PROFILE;
    }

    @SessionIdRequired
    @PutMapping("/users/profile")
    public String updateUserProfile(@Valid UserCreationDto dto,
                                    Errors errors,
                                    HttpSession session) throws Exception {
        validateParams(errors);
        long id = (long) session.getAttribute("sessionedUserId");
        userService.update(id, dto);
        return "redirect:" + Url.USERS;
    }

    @SessionIdRequired
    @GetMapping("/users/profile")
    public String getProfileUpdateForm(Model model,
                                       HttpSession session,
                                       RedirectAttributes attr) {
        long currentUserId = (long) session.getAttribute("sessionedUserId");
        model.addAttribute("user", userService.findById(currentUserId));
        return View.USER_UPDATE_FORM;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return View.USER_LOGIN;
    }

    @PostMapping("/login")
    public String login(String email,
                        String password,
                        HttpSession session) {
        var user = userService.findByEmail(email);
        userService.validatePassword(password, user.getPassword());
        session.setAttribute("sessionedUserId", user.getId());
        return "redirect:" + Url.INDEX;
    }

    @SessionIdRequired
    @GetMapping("/users/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:" + Url.USER_LOGIN;
    }

    private UserDto getUserById(Long id) {
        return userService.findById(id);
    }

    private void validateParams(Errors errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> log.warn("{}", e.getDefaultMessage()));
            throw new IllegalArgumentException("입력값이 올바르지 않습니다");
        }
    }
}
