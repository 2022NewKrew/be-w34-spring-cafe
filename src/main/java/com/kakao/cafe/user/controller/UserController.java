package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.dto.request.UserCreateRequest;
import com.kakao.cafe.user.dto.request.UserUpdateRequest;
import com.kakao.cafe.user.dto.response.UserInfoResponse;
import com.kakao.cafe.user.exception.DuplicateUserIdException;
import com.kakao.cafe.user.exception.UserNotFoundException;
import com.kakao.cafe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 로그인 페이지 접속 [GET]
     */
    @GetMapping("/users/login")
    @ResponseStatus(HttpStatus.OK)
    public String getLoginPage() {
        log.info("[GET] /user/login - 로그인 페이지 접속");
        return "user/login";
    }

    /**
     * 회원가입 페이지 접속 [GET]
     */
    @GetMapping("/users/join")
    @ResponseStatus(HttpStatus.OK)
    public String getJoinPage() {
        log.info("[GET] /user/join - 회원가입 페이지 접속");
        return "user/join";
    }

    /**
     * 회원가입 요청 [POST]
     * @param req: 회원가입 정보
     * @throws DuplicateUserIdException: 회원가입 요청한 UserId가 이미 존재하는 ID일 경우 발생
     */
    @PostMapping("/users")
    public String createUser(UserCreateRequest req) {
        log.info("[POST] /user - 유저 회원가입 요청");
        this.userService.createUser(req);

        return "redirect:/users";
    }

    /**
     * 유저 리스트 페이지 접속 [GET]
     */
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public String getUserListPage(Model model) {
        log.info("[GET] /user - 유저 리스트 페이지 접속");
        List<UserInfoResponse> userList = this.userService.getUserList();
        model.addAttribute("users", userList);
        return "user/list";
    }

    /**
     * 사용자 상세 정보 페이지 접속 [GET]
     * @param id: 보고자 하는 사용자의 ID(PK)
     * @throws UserNotFoundException: 해당 ID 의 User 가 존재하지 않을 경우 발생
     */
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getUserProfilePage(Model model, @PathVariable("id") Long id) {
        log.info("[GET] /user/{} - (id: {}) 유저 상세정보(프로필) 페이지 접속", id, id);

        UserInfoResponse userProfile = this.userService.getUserProfile(id);
        model.addAttribute("user", userProfile);

        return "user/profile";
    }

    /**
     * 사용자 정보 수정 페이지 접속 [GET]
     * @param id: 수정하고자 하는 유저의 ID(PK)
     * @throws UserNotFoundException: 해당 ID 의 User 가 존재하지 않을 경우 발생
     */
    @GetMapping("/users/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getUserUpdatePage(Model model, @PathVariable("id") Long id) {
        log.info("[GET] /user/update/{} - (id: {}) 유저 정보 수정 페이지 접속", id, id);

        UserInfoResponse userProfile = this.userService.getUserProfile(id);
        model.addAttribute("user", userProfile);

        return "/user/update";
    }

    /**
     * 회원 프로필 수정 요청 [POST]
     * @param req: 회원 프로필 수정 정보
     * @param id: 수정하고자 하는 유저의 ID(PK)
     */
    @PostMapping("/users/update/{id}")
    public String updateUser(UserUpdateRequest req,  @PathVariable("id") Long id) {
        log.info("[POST] /user/update/{} - (id: {}) 유저 정보 수정", id, id);

        this.userService.updateUser(id, req);
        return "redirect:/users";
    }
}
