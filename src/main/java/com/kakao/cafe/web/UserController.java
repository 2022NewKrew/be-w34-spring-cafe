package com.kakao.cafe.web;

import com.kakao.cafe.domain.user.UserInfo;
import com.kakao.cafe.domain.user.UserList;
import com.kakao.cafe.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("user/form.html")
    public String signUpPage() {
        logger.info("signup form");
        return "user/form";
    }

    @PostMapping("user/create")
    public String signUp(UserInfo userInfo) {
        logger.info("user:{}", userInfo);
        UserService.userSingUp(userInfo);
        return "redirect:/users";
    }

    @GetMapping("users")
    public String viewUserList(Model model) {
        UserList userList = UserList.getInstance();
        model.addAttribute("users", userList.getUserList());
        model.addAttribute("size", userList.getSize());
        return "/user/list";
    }

    @GetMapping("users/{userId}")
    public String viewUserProfile(@PathVariable String userId, Model model) {
        logger.info("profile:{}", model.addAttribute("userInfo", UserList.getInstance().findById(userId)));
        return "/user/profile";
    }

}
