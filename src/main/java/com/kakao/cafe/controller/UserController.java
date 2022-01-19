package com.kakao.cafe.controller;

import com.kakao.cafe.config.auth.LoginUser;
import com.kakao.cafe.dto.RequestUserDto;
import com.kakao.cafe.dto.SessionUser;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    /*
     * 회원가입
     */
    @PostMapping("/users")
    public String join(@ModelAttribute RequestUserDto userDto) {

        log.info("POST /users {}", userDto);

        userService.join(userDto);
        return "redirect:/users";
    }

    /*
     * 유저 리스트 조회
     */
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        log.info("GET /users");
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("countOfUser", userService.getCountOfUser());
        return "user/list";
    }

    /*
     * 유저 상세 정보 조회
     */
    @GetMapping("/users/{id}")
    public String getUserProfile(@PathVariable long id, Model model, @LoginUser SessionUser user) {
        log.info("GET /users/{}", id);

        if (id == user.getId()) {
            model.addAttribute("myId", user.getId());
        }

        model.addAttribute("user", userService.getUserById(id));

        return "user/profile";
    }

    /*
     * 유저 상세 정보 수정 페이지 조회
     */
    @GetMapping("/users/{id}/update")
    public String showEditUserPage(@PathVariable long id, Model model, @LoginUser SessionUser user) {
        log.info("GET /users/{}/update", id);

        if (user.getId() != id) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }
        model.addAttribute("user", userService.getUserById(id));
        return "user/updateForm";
    }

    /*
     * 유저 상세 정보 수정
     */
    @PutMapping("/users/{id}/update")
    public String editUser(@PathVariable long id, @ModelAttribute RequestUserDto userDto, @LoginUser SessionUser user) {
        log.info("PUT /users/{}/update : {}", id, userDto);

        if (user.getId() != id) {
            //권한이 없음. 잘못된 접근
            return "redirect:/login.html";
        }

        userService.updateUser(id, userDto);
        return "redirect:/users";

    }

    /*
     * 로그인
     */
    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        SessionUser user = userService.login(userId.trim(), password.trim());
        session.setAttribute("sessionedUser", user);
        return "redirect:/";
    }

    /*
     * 로그아웃
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}
