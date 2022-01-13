package com.kakao.cafe.controller;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserListDto;
import com.kakao.cafe.dto.UserProfileDto;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.view.UserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private static final UserService userService = new UserService();
    private static final UserView userView = new UserView();

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<UserListDto> userList = userService.getUserList();
        userView.getUsersView(model, userList);
        return "user/list";
    }

    @PostMapping("/user/create")
    public String postUserCreate(Model model, User user) {
        userService.createUser(user);

        return "redirect:/users";
    }

    @GetMapping("/user/{id}")
    public String getUserId(Model model, @PathVariable("id") String id) {
        UserProfileDto userProfileDto = userService.readUserProfileDto(id);
        userView.getUserIdView(model, userProfileDto);

        return "user/profile";
    }

    @GetMapping("/user/form")
    public String getUserForm() {
        return "user/form";
    }

    @GetMapping("/user/login")
    public String getUserLogin() {
        return "user/login";
    }

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/")
    public String getRoot() {
        return "redirect:/index";
    }
}
