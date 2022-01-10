package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.dto.request.UserCreateRequest;
import com.kakao.cafe.user.dto.response.UserInfoResponse;
import com.kakao.cafe.user.exception.DuplicateUserIdException;
import com.kakao.cafe.user.exception.UserNotFoundException;
import com.kakao.cafe.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 로그인 페이지 접속 [GET]
     */
    @GetMapping("/user/login")
    public String getLoginPage() {
        logger.info("[GET] /user/login - 로그인 페이지 접속");
        return "user/login";
    }

    /**
     * 회원가입 페이지 접속 [GET]
     */
    @GetMapping("/user/join")
    public String getJoinPage() {
        logger.info("[GET] /user/join - 회원가입 페이지 접속");
        return "user/join";
    }

    /**
     * 회원가입 요청 [POST]
     * @param req: 회원가입 정보
     * @throws DuplicateUserIdException: 회원가입 요청한 UserId가 이미 존재하는 ID일 경우 발생
     */
    @PostMapping("/user/join")
    public String createUser(UserCreateRequest req, Model model) {
        logger.info("[POST] /user - 유저 회원가입 요청");
        try {
            userService.createUser(req);
            List<UserInfoResponse> userList = userService.getUserList();
            model.addAttribute("users", userList);
            return "user/list";
        } catch(DuplicateUserIdException e) {
            logger.info("[ERROR] - {}", e.getMessage());
            return "../static/index";
        }
    }

    /**
     * 유저 리스트 페이지 접속 [GET]
     */
    @GetMapping("/user")
    public String getUserListPage(Model model) {
        logger.info("[GET] /user - 유저 리스트 페이지 접속");
        List<UserInfoResponse> userList = userService.getUserList();
        model.addAttribute("users", userList);
        return "user/list";
    }

    /**
     * 사용자 상세 정보 페이지 접속 [GET]
     * @param id: 요청 사용자 ID(PK)
     * @throws UserNotFoundException: 해당 ID의 User가 존재하지 않을 경우 발생
     */
    @GetMapping("/user/{id}")
    public String getUserProfilePage(Model model, @PathVariable("id") Integer id) {
        logger.info("[GET] /user/{} - (id: {}) 유저 상세정보(프로필) 페이지 접속", id, id);
        try {
            UserInfoResponse userProfile = userService.getUserProfile(id);
            model.addAttribute("user", userProfile);

            return "user/profile";
        } catch(UserNotFoundException e) {
            logger.info("[ERROR] - {}", e.getMessage());
            return "user/list";
        }
    }
}
