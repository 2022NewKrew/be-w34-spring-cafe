package com.kakao.cafe.interfaces.user;

import com.kakao.cafe.application.UserService;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.interfaces.common.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * {@link User} 목록 조회
     *
     * @param model
     */
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAllUser());
        return "user_list";
    }

    /**
     * {@link User} 프로필 조회
     *
     * @param id    조회할 유저의 ID
     * @param model
     */
    @GetMapping("/{id}")
    public String userProfile(@PathVariable long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user_profile";
    }

    /**
     * 회원가입
     *
     * @param userDto 회원가입시 입력한 정보
     */
    @PostMapping("/new")
    public String signup(UserDto userDto) {
        userService.signup(userDto);
        return "redirect:/users";
    }
}
