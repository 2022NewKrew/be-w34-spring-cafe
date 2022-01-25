package com.kakao.cafe.web.controller;

import com.kakao.cafe.exception.IllegalUserInputException;
import com.kakao.cafe.exception.IllegalLoginInputException;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.web.dto.UserDTO;
import com.kakao.cafe.web.dto.UserResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user/list")
    public String getUserList(Model model) {
        model.addAttribute("userListSize", userService.getUserListSize());
        model.addAttribute("userList", userService.getUserDTOList());
        return "/user/list";
    }

    @PostMapping(value = "/user/create")
    public String postSignUp(String userId, String password, String email) {
        userService.createUser(UserDTO.newInstance(userId, password, email));
        log.info("userList:{}", userService.getUserDTOList());
        return "redirect:/user/list";
    }

    @PostMapping(value = "/user/login_check")
    public String login(String userId, String password, HttpSession session) {
        Optional<UserResponseDTO> userResponseDTO = userService.getSessionUserDTO(userId, password);

        if (userResponseDTO.isPresent()) {
            session.setAttribute("sessionUser", userResponseDTO.get());
            return "redirect:/user/login_success";
        } else
            return "redirect:/user/login_failed";
    }

    @GetMapping(value = "/user/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("sessionUser");
        return "redirect:/index";
    }

    @GetMapping(value = "user/profile/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model) {
        UserResponseDTO userResponseDTO = userService.getUserByUserId(userId);
        log.info("userDTO:{}", userResponseDTO);
        model.addAttribute("user", userResponseDTO);
        return "/user/profile";
    }

    @ExceptionHandler(IllegalLoginInputException.class)
    ResponseEntity<String> handleIllegalUserInput(IllegalLoginInputException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalUserInputException.class)
    ResponseEntity<String> handleIllegalUserInput(IllegalUserInputException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
