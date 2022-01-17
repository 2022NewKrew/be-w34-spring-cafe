package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.RequestUserDto;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    /*
    * 회원가입
    */
    @PostMapping("/users")
    public String join(@ModelAttribute RequestUserDto userDto) {

        logger.info("POST /users {}", userDto);

        userService.join(userDto);
        return "redirect:/users";
    }

    /*
    * 유저 리스트
    */
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        logger.info("GET /users");
        model.addAttribute("users", userService.findUsers());
        model.addAttribute("countOfUser", userService.getCountOfUser());
        return "user/list";
    }

    /*
    * 유저 상세 정보
    */
    @GetMapping("/users/{id}")
    public String getUserProfile(@PathVariable int id, Model model) {
        logger.info("GET /users/{}", id);
        model.addAttribute("user", userService.findOne(id));
        return "user/profile";
    }

    /*
    * 유저 상세 정보 수정 페이지
    */
    @GetMapping("/users/{id}/form")
    public String showEditUserPage(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "user/updateForm";
    }

    /*
    * 유저 상세 정보 수정
    */
    @PostMapping("/users/{id}/update")
    public String editUser(@PathVariable int id, @ModelAttribute RequestUserDto userDto) {
        userService.updateUser(id, userDto);
        return "redirect:/users";
    }

    /*
     * 로그인
     */
    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        if (userService.login(userId.trim(), password.trim())) {
            session.setAttribute("sessionedUser", userId);
        }
        return "redirect:/";
    }


}
