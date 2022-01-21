package com.kakao.cafe.user.presentation;

import com.kakao.cafe.user.application.JoinService;
import com.kakao.cafe.user.application.LoginService;
import com.kakao.cafe.user.application.SearchUserService;
import com.kakao.cafe.user.application.UpdateUserInfoService;
import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.entity.UserInfo;
import com.kakao.cafe.user.presentation.dto.JoinRequest;
import com.kakao.cafe.user.presentation.dto.UpdateUserInfoRequest;
import com.kakao.cafe.user.presentation.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final LoginService loginService;
    private final JoinService joinService;
    private final SearchUserService searchUserService;
    private final UpdateUserInfoService updateUserInfoService;

    private final ModelMapper modelMapper;

    @GetMapping("/loginForm")
    public String loginForm(){
        logger.info("로그인을 시도합니다.");
        return "user/loginForm";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession httpSession){
        User user = loginService.login(userId, password);
        httpSession.setAttribute("userId", userId);
        httpSession.setAttribute("name", user.getUserInfo().getName());
        logger.info("사용자 {}가 로그인 하였습니다.", userId);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        String userId = getSessionUserId(session);
        logger.info("사용자 {}가 로그아웃합니다.", userId);
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("")
    public String listUsers(Model model){
        logger.info("사용자 목록을 조회합니다.");
        List<User> users = searchUserService.getAllUsers();
        List<UserDto> userDtos = List.of(modelMapper.map(users, UserDto[].class));

        model.addAttribute("users", userDtos);
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String  getUserInfo(@PathVariable String userId, Model model){
        logger.info("사용자 {}의 상세정보를 조회합니다.", userId);
        return "redirect:/";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        logger.info("회원가입 시도합니다.");

        return "user/joinForm";
    }

    @PostMapping("")
    public String join(JoinRequest joinRequest, Model model){
        User user = modelMapper.map(joinRequest, User.class);
        joinService.save(user);
        logger.info("사용자 {}가 새로 가입하였습니다.", user.getUserId());

        model.addAttribute("user", modelMapper.map(user, UserDto.class));
        return "user/join_success";
    }

    @GetMapping("/updateForm")
    public String updateForm(HttpSession session, Model model){
        String userId = getSessionUserId(session);
        logger.info("사용자 {}가 정보 갱신을 시도합니다.", userId);
        model.addAttribute("id", userId);
        return "user/updateForm";
    }

    @PostMapping("/update")
    public String updateInfo(UpdateUserInfoRequest updateRequest, HttpSession session){
        String userId = getSessionUserId(session);
        updateUserInfoService.updateUserInfo(userId, modelMapper.map(updateRequest, UserInfo.class));
        logger.info("사용자 {}의 정보 갱신을 완료하였습니다.", userId);

        return "redirect:/users";
    }

    public String getSessionUserId(HttpSession session){
        return Objects.requireNonNull((String) session.getAttribute("userId"), "로그인을 먼저 하세요.");
    }
}
