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

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

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
            logger.error("[UserController > form] " + e.getMessage());
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
        if(userAccountService.findOne(userId).isEmpty()){
            logger.error("[UserController > profile] DB 에서 유저 계정에서 " + userId + "로 검색에 실패했습니다.");
            return "/user/list";
        }
        UserAccount userAccount = userAccountService.findOne(userId).get();

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
            Optional<UserAccount> userAccount = userAccountService.updateUserAccount(userAccountDTO, curPassword);

            if(userAccount.isEmpty()){
                logger.error("[UserController > updateForm] 계정 프로필 업데이트 요청 중 기존 비밀번호와 입력한 비밀번호가 다릅니다.");
                return "redirect:/user";
            }
        } catch (InputMismatchException e) {
            logger.error("[Controller > updateForm] " + e.getMessage());
        }

        return "redirect:/user";
    }
}
