package com.kakao.cafe.module.controller;

import com.kakao.cafe.module.service.InfraService;
import com.kakao.cafe.module.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.kakao.cafe.module.model.dto.UserDtos.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final InfraService infraService;

    @PostMapping
    public String signUp(UserSignUpDto userSignUpDto) {
        userService.signUp(userSignUpDto);
        logger.info("Create User : {}", userSignUpDto.getName());
        return "redirect:/users";
    }

    @GetMapping
    public String userList(Model model) {
        List<UserDto> userList = userService.userList();
        model.addAttribute("userList", userList);
        logger.info("Retrieve {} users", userList.size());
        return "user/list";
    }

    @GetMapping("/{id}")
    public String profile(@PathVariable Long id, Model model) {
        UserDto user = userService.findUser(id);
        model.addAttribute("user", user);
        logger.info("Get User Profile : {}", id);
        return "user/profile";
    }

    @PostMapping("/sign-in")
    public String signIn(UserSignInDto userSignInDto, HttpSession session) {
        UserDto userDto = userService.signIn(userSignInDto);
        session.setAttribute("sessionUser", userDto);
        logger.info("Sign in User : {}", userDto.getName());
        return "redirect:/";
    }

    @GetMapping("/sign-out")
    public String signOut(HttpSession session) throws HttpSessionRequiredException {
        UserDto userDto = infraService.retrieveUserSession(session);
        session.invalidate();
        logger.info("Sign out User : {}", userDto.getName());
        return "redirect:/";
    }

    @GetMapping("/{id}/form")
    public String getUpdateForm(@PathVariable Long id, Model model, HttpSession session) throws HttpSessionRequiredException {
        infraService.validateSession(session, id);
        UserDto user = userService.findUser(id);
        model.addAttribute("user", user);
        logger.info("Get User Update Form : {}", id);
        return "user/updateForm";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, UserUpdateDto userUpdateDto, HttpSession session) throws HttpSessionRequiredException {
        infraService.validateSession(session, id);
        userService.updateUser(id, userUpdateDto);
        logger.info("Update User : {}", id);
        return "redirect:/users";
    }
}
