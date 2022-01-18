package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserResponseDTO;
import com.kakao.cafe.dto.UserRequestDTO;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
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
        UserResponseDTO user = userService.read(userId).get();
        model.addAttribute("user", user);
        return "user/profile";
    }

    @PostMapping()
    public String addUser(UserRequestDTO userRequestDto) {
        logger.info("addUser: {}, {}", userRequestDto.getUserId(), userRequestDto.getPassword());
        userService.create(userRequestDto);
        return "redirect:";
    }

    @GetMapping("/{userId}/form")
    public String updateUser(@PathVariable String userId, Model model) {
        logger.info("updateUser(GET): {}", userId);
        UserResponseDTO user = userService.read(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        model.addAttribute("user", user);
        logger.info("update {}, {}, {}", user.getUserId(), user.getName(), user.getEmail());
        return "user/updateForm";
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable String userId, UserRequestDTO userRequestDto) {
        logger.info("updateUser(PUT): {}, {}, {}", userId, userRequestDto.getPassword(), userRequestDto.getName());
        userService.update(userRequestDto);
        return "redirect:";
    }
}
