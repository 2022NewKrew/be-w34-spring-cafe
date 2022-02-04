package com.kakao.cafe.web;

import com.kakao.cafe.error.ErrorResponse;
import com.kakao.cafe.exception.InvalidPasswordException;
import com.kakao.cafe.exception.UserDuplicateException;
import com.kakao.cafe.exception.UserNotRegisteredException;
import com.kakao.cafe.service.user.UserService;
import com.kakao.cafe.web.dto.user.UserCreateRequestDto;
import com.kakao.cafe.web.dto.user.UserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class UserController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        return this.userService.userLogin(userId, password, session);
    }


    @PostMapping("user/create")
    public String signUp(UserCreateRequestDto userCreateRequestDto) {
        logger.info("user:{}", userCreateRequestDto);
        this.userService.userSingUp(userCreateRequestDto);
        return "redirect:/users";
    }

    @GetMapping("users")
    public String viewUserList(Model model) {
        List<UserResponseDto> userList = this.userService.findAll();
        model.addAttribute("users", userList);
        model.addAttribute("size", userList.size());
        return "/user/list";
    }

    @GetMapping("users/{userId}")
    public String viewUserProfile(@PathVariable String userId, Model model) {
        logger.info("profile:{}", model.addAttribute("userInfo", userService.findById(userId)));
        return "/user/profile";
    }

    @GetMapping("user/logout")
    public String userLogout(HttpSession session) {
        session.removeAttribute("sessionUser");
        return "redirect:/";
    }


    @ExceptionHandler({UserNotRegisteredException.class})
    public ResponseEntity<ErrorResponse> handleUserNotRegisteredException(UserNotRegisteredException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode()), HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler({UserDuplicateException.class})
    public ResponseEntity<ErrorResponse> handleUserDuplicatedException(UserDuplicateException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode()), HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler({InvalidPasswordException.class})
    public ResponseEntity<ErrorResponse> handleInvalidPassword(InvalidPasswordException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode()), HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

}
