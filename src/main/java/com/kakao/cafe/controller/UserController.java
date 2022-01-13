package com.kakao.cafe.controller;

import com.kakao.cafe.domain.UserAccount;
import com.kakao.cafe.domain.UserAccountDTO;
import com.kakao.cafe.service.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.openmbean.InvalidKeyException;
import java.util.List;

/**
 * author    : brody.moon
 * version   : 1.0
 * User 관련 컨트롤러 클래스입니다.
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserAccountService userAccountService;

    @Autowired
    public UserController(UserAccountService userAccountService){
        this.userAccountService = userAccountService;
    }

    @GetMapping("form")
    public String form(){
        return "/user/form";
    }

    @PostMapping("form")
    public String form(UserAccountDTO userAccountDTO){
        try {
            userAccountService.join(userAccountDTO);
        } catch (IllegalStateException e) {
            logger.info(e.getMessage());
            return "redirect:/user";
        }

        return "redirect:/user";
    }

    @GetMapping
    public String userInfo(Model model){
        List<UserAccount> userAccounts = userAccountService.findAll();

        model.addAttribute("user_accounts", userAccounts);

        return "/user/list";
    }

    @GetMapping("{userId}")
    public String profile(@PathVariable("userId") String userId, Model model){
        UserAccount userAccount = null;
        try {
            userAccount = userAccountService.findOne(userId)
                    .orElseThrow(() -> new InvalidKeyException("아이디를 찾을 수 없습니다."));
        } catch (InvalidKeyException e) {
            logger.info("아이디 찾기 에러");
            return "/user/list";
        }

        model.addAttribute("user_account", userAccount);
        return "/user/profile";
    }

    @GetMapping("{userId}/form")
    public String updateForm(@PathVariable("userId") String userId){
        return "/user/update_form";
    }

    @PostMapping("{userId}/form")
    public String updateForm(@PathVariable("userId") String userId, String curPassword, UserAccountDTO userAccountDTO){
        try {
            userAccountService.updateUserAccount(userAccountDTO, curPassword)
                    .orElseThrow(() -> new IllegalAccessError("비밀 번호가 일치하지 않습니다"));
        } catch (IllegalAccessError e) {
            logger.info("비밀 번호 불일치");
            return "/user/list";
        }
        return "redirect:/user";
    }
}
