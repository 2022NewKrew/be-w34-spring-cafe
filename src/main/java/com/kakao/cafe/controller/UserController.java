package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.RequestUserDto;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.vo.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
     * 유저 리스트
     */
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        log.info("GET /users");
        model.addAttribute("users", userService.findUsers());
        model.addAttribute("countOfUser", userService.getCountOfUser());
        return "user/list";
    }

    /*
     * 유저 상세 정보
     */
    @GetMapping("/users/{id}")
    public String getUserProfile(@PathVariable long id, Model model, HttpSession session) {
        log.info("GET /users/{}", id);
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            //로그인 후 이용할 수 있습니다.?
            return "redirect:/login.html";
        }
        UserVo userVo = (UserVo) value;

        if (id == userVo.getId()){
            model.addAttribute("myId", userVo.getId());
        }

        model.addAttribute("user", userService.findOne(id));

        return "user/profile";
    }

    /*
     * 유저 상세 정보 수정 페이지 로드
     */
    @GetMapping("/users/{id}/update")
    public String showEditUserPage(@PathVariable long id, Model model, HttpSession session) {
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            //로그인 후 이용할 수 있습니다.?
            return "redirect:/login.html";
        }
        UserVo userVo = (UserVo) value;

        if (userVo.getId() != id) {
            //본인 정보만 수정할 수 있습니다.
            return "redirect:/login.html";
        }

        model.addAttribute("user", userService.findOne(userVo.getUserId()));
        return "user/updateForm";

    }

    /*
     * 유저 상세 정보 수정
     */
    @PutMapping("/users/{id}/update")
    public String editUser(@PathVariable long id, @ModelAttribute RequestUserDto userDto, HttpSession session) {
        // TO DO : PathVariable id 어떻게 할 건지 로그인 api 예제 찾아보고 변경
        log.info("PUT /users/{}/update : {}", id, userDto);

        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            //로그인 후 이용할 수 있습니다.?
            return "redirect:/login.html";
        }
        UserVo userVo = (UserVo) value;

        if (userVo.getId() != id){
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
        UserVo userVo = userService.login(userId.trim(), password.trim());
        session.setAttribute("sessionedUser", userVo);
        log.info(">>>> {}", userVo.getId());
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
