package com.kakao.cafe.controller;

import com.kakao.cafe.controller.interceptor.ValidateLogin;
import com.kakao.cafe.dto.UserRequestDTO;
import com.kakao.cafe.dto.UserResponseDTO;
import com.kakao.cafe.dto.UserUpdateDTO;
import com.kakao.cafe.error.exception.AuthorizationException;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private static final String SESSION_USER = "sessionUser";
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping()
    public String getUserList(Model model) {
        List<UserResponseDTO> users = userService.readAll();
        if(users.size() > 0) {
            logger.info("getUserList: {}, {}, {}", users.get(0).getId(), users.get(0).getUserId(), users.get(0).getName());
        }
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable String userId, Model model) {
        logger.info("getUser: {}", userId);
        UserResponseDTO user = userService.read(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @PostMapping()
    public String addUser(@Validated UserRequestDTO userRequestDto) {
        logger.info("addUser: {}, {}", userRequestDto.getUserId(), userRequestDto.getPassword());
        userService.create(userRequestDto);
        return "redirect:";
    }

    @ValidateLogin
    @GetMapping("/{userId}/form")
    public String updateUser(@PathVariable String userId, Model model, HttpSession session) {
        logger.info("updateUser(GET): {} {}", userId, session.getAttribute(SESSION_USER));
        if(!userId.equals(session.getAttribute(SESSION_USER))) {
            throw new AuthorizationException();
        }
        UserResponseDTO user = userService.read(userId);
        model.addAttribute("user", user);
        logger.info("update {}, {}, {}", user.getUserId(), user.getName(), user.getEmail());
        return "user/updateForm";
    }

    @ValidateLogin
    @PutMapping("/{userId}")
    public String updateUser(@PathVariable String userId, @Validated UserUpdateDTO user) {
        logger.info("updateUser(PUT): {}, {}, {}, {}", userId, user.getPassword(), user.getPasswordCheck(), user.getName());
        userService.update(user);
        return "redirect:";
    }
}
