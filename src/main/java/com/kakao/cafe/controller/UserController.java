package com.kakao.cafe.controller;

import com.kakao.cafe.model.data_storage.AccountTable;
import com.kakao.cafe.model.UserAccount;
import com.kakao.cafe.model.UserAccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

/**
 * author    : brody.moon
 * version   : 1.0
 * User 관련 컨트롤러 클래스입니다.
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("form")
    public String form(){
        return "/user/form";
    }

    @PostMapping("form")
    public String form(UserAccountDTO userAccountDTO){
        logger.info(userAccountDTO + "");
        UserAccount userAccount = UserAccount.createUserAccount(userAccountDTO);

        if(!Objects.isNull(userAccount))
            AccountTable.saveUserInto(userAccountDTO.getUserID(), userAccount);

        return "redirect:/user";
    }

    @GetMapping
    public String userInfo(Model model){
        List<UserAccountDTO> userAccounts = AccountTable.allUserAccountInfo();

        model.addAttribute("user_accounts", userAccounts);

        return "/user/list";
    }

    @GetMapping("{userID}")
    public String profile(@PathVariable("userID") String userID, Model model){
        UserAccountDTO userAccountDTO = AccountTable.lookUpUserInfo(userID).toUserAccountDTO();

        model.addAttribute("user_account", userAccountDTO);
        return "user/profile";
    }
}
