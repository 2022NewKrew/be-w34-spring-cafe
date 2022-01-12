package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.dto.request.UserCreateRequest;
import com.kakao.cafe.user.dto.request.UserUpdateRequest;
import com.kakao.cafe.user.dto.response.UserInfoResponse;
import com.kakao.cafe.user.exception.DuplicateUserIdException;
import com.kakao.cafe.user.exception.PasswordNotMatchedException;
import com.kakao.cafe.user.exception.UserNotFoundException;
import com.kakao.cafe.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final Logger logger;

    private UserController(UserService userService) {
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(UserController.class);
    }

    /**
     * 로그인 페이지 접속 [GET]
     */
    @GetMapping("/users/login")
    public String getLoginPage() {
        this.logger.info("[GET] /user/login - 로그인 페이지 접속");
        return "user/login";
    }

    /**
     * 회원가입 페이지 접속 [GET]
     */
    @GetMapping("/users/join")
    public String getJoinPage() {
        this.logger.info("[GET] /user/join - 회원가입 페이지 접속");
        return "user/join";
    }

    /**
     * 회원가입 요청 [POST]
     * @param req: 회원가입 정보
     * @throws DuplicateUserIdException: 회원가입 요청한 UserId가 이미 존재하는 ID일 경우 발생
     */
    @PostMapping("/users")
    public String createUser(UserCreateRequest req, Model model) {
        this.logger.info("[POST] /user - 유저 회원가입 요청");
        try {
            this.userService.createUser(req);
            return "redirect:/users";
        } catch(DuplicateUserIdException e) {
            this.logger.error("[ERROR] - {}", e.getMessage());
            return "redirect:/";
        }
    }

    /**
     * 유저 리스트 페이지 접속 [GET]
     */
    @GetMapping("/users")
    public String getUserListPage(Model model) {
        this.logger.info("[GET] /user - 유저 리스트 페이지 접속");
        List<UserInfoResponse> userList = this.userService.getUserList();
        model.addAttribute("users", userList);
        return "user/list";
    }

    /**
     * 사용자 상세 정보 페이지 접속 [GET]
     * @param id: 요청 사용자 ID(PK)
     * @throws UserNotFoundException: 해당 ID 의 User 가 존재하지 않을 경우 발생
     */
    @GetMapping("/users/{id}")
    public String getUserProfilePage(Model model, @PathVariable("id") Integer id) {
        this.logger.info("[GET] /user/{} - (id: {}) 유저 상세정보(프로필) 페이지 접속", id, id);
        try {
            UserInfoResponse userProfile = this.userService.getUserProfile(id);
            model.addAttribute("user", userProfile);

            return "user/profile";
        } catch(UserNotFoundException e) {
            this.logger.error("[ERROR] - {}", e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/update/{id}")
    public String getUserUpdatePage(Model model, @PathVariable("id") Integer id) {
        this.logger.info("[GET] /user/update/{} - (id: {}) 유저 정보 수정 페이지 접속", id, id);
        try {
            UserInfoResponse userProfile = this.userService.getUserProfile(id);
            model.addAttribute("user", userProfile);

            return "/user/update";
        } catch(UserNotFoundException e) {
            this.logger.error("[ERROR] - {}", e.getMessage());
            return "redirect:/users";
        }
    }

    @PostMapping("/users/update/{id}")
    public String updateUser(UserUpdateRequest req,  @PathVariable("id") Integer id) {
        this.logger.info("[POST] /user/update/{} - (id: {}) 유저 정보 수정", id, id);
        try {
            this.userService.updateUser(id, req);
            return "redirect:/users";
        } catch(PasswordNotMatchedException e) {
            this.logger.error("[ERROR] - {}", e.getMessage());
            return "redirect:/";
        }
    }
}
