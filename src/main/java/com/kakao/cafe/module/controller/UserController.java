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

    @GetMapping("/{id}/form")
    public String getUserUpdateForm(@PathVariable Long id, Model model, HttpSession session) throws HttpSessionRequiredException {
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
