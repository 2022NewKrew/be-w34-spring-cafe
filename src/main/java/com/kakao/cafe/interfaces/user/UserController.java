package com.kakao.cafe.interfaces.user;

import com.kakao.cafe.application.user.FindUserService;
import com.kakao.cafe.application.user.SignUpUserService;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserVo;
import com.kakao.cafe.interfaces.user.dto.UserMapper;
import com.kakao.cafe.interfaces.user.dto.request.JoinUserRequestDto;
import com.kakao.cafe.interfaces.user.dto.response.UserListResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final FindUserService findUserService;
    private final SignUpUserService signUpUserService;

    public UserController(FindUserService findUserService, SignUpUserService signUpUserService) {
        this.findUserService = findUserService;
        this.signUpUserService = signUpUserService;
    }

    @GetMapping("")
    public String getAllUser(Model model) {
        List<User> userList = findUserService.findAllUser();
        List<UserListResponseDto> joinUserRequestDtoList = UserMapper.convertEntityListToResponseDtoList(userList);
        model.addAttribute("users", joinUserRequestDtoList);
        return "user/list";
    }

    @PostMapping("")
    public String joinUser(JoinUserRequestDto joinUserRequestDto) {
        UserVo user = UserMapper.convertJoinUserDtoToVo(joinUserRequestDto);
        signUpUserService.join(user);
        return "redirect:/users";
    }

    @GetMapping("/{userId}")
    public String getUserByUserId(@PathVariable String userId, Model model) {
        User user = findUserService.findByUserId(userId);
        model.addAttribute("name", user.getName());
        model.addAttribute("email", user.getEmail());

        return "user/profile";
    }

}