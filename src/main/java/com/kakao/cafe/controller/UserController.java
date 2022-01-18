package com.kakao.cafe.controller;

import com.kakao.cafe.aop.UserAccountAuthCheck;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
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

        return "redirect:/user/login";
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
            return "redirect:/user";
        }
        UserAccount userAccount = userAccountService.findOne(userId).get();

        model.addAttribute("user_account", userAccount);
        return "/user/profile";
    }

    @UserAccountAuthCheck
    @GetMapping("{userId}/form")
    public String updateForm(@PathVariable("userId") String userId, HttpSession session){
        return "/user/update_form";
    }

    @UserAccountAuthCheck
    @PutMapping("{userId}/form")
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

    @GetMapping("login")
    public String login(){
        return "/user/login";
    }

    @PostMapping("login")
    public String login(String userId, String password, HttpSession session){
        Optional<UserAccount> findUserAccount = userAccountService.findOne(userId);

        if(findUserAccount.isEmpty()){
            logger.error("[UserController > login] DB 에서 유저 계정에서 " + userId + "로 검색에 실패했습니다.");
            return "redirect:/user/login/failed";
        }

        UserAccount userAccount = findUserAccount.get();

        if(!userAccountService.isPasswordEqual(userAccount, password)){
            logger.error("[UserController > login] 로그인 시 입력한 비밀번호가 DB와 일치하지 않습니다.");
            return "redirect:/user/login/failed";
        }

        session.setAttribute("sessionedUser", userAccount);
        return "redirect:/";
    }

    @GetMapping("login/failed")
    public String loginFailed(){
        return "/user/login_failed";
    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();

        return "redirect:/";
    }
}
