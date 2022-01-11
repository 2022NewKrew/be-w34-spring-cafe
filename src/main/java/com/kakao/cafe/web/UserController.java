package com.kakao.cafe.web;

import com.kakao.cafe.domain.UserInfo;
import com.kakao.cafe.domain.UserList;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("user/form.html")
    public String signUpPage(){
        logger.info("signup form");
        return "user/form";
    }

    @PostMapping("user/create")
    public String signUp(UserInfo userInfo){
        logger.info("user:{}",userInfo);
        UserService.userSingUp(userInfo);
        logger.info("userList:{}", UserList.getInstance());
        return "redirect:/users";
    }
}
