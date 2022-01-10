package com.kakao.cafe.controller;

import com.kakao.cafe.model.User;
import com.kakao.cafe.model.UserList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user/create")
    public String signUp(User user) {
        UserList.addUserList(user);
        logger.info("user create : {}", user.getUserId());
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String getUserList(){
        return "/user/list";
    }

    @GetMapping("/user/form")
    public String signUp(Model model){
        return "/user/form";
    }
}
