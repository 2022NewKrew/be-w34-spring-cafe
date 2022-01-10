package com.kakao.cafe.controller;

import com.kakao.cafe.model.DataStorage;
import com.kakao.cafe.model.UserAccount;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("form")
    public String form(){
        return "user/form";
    }

    @PostMapping("form")
    public String form(String userId, String password, String name, String email, Model model){
        UserAccount userAccount = UserAccount.createUserAccount(userId, password, name, email);

        if(!Objects.isNull(userAccount))
            DataStorage.saveUserInto(userId, userAccount);

        return "redirect:/user";
    }

    @GetMapping("")
    public String userInfo(Model model){
        List<UserAccount> userAccounts = DataStorage.allUserAccountInfo();

        model.addAttribute("user_accounts", userAccounts);
        logger.info(userAccounts.toString());
        return "user/list";
    }
}
