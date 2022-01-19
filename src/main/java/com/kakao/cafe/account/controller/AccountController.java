package com.kakao.cafe.account.controller;

import com.kakao.cafe.account.dto.AccountDto;
import com.kakao.cafe.account.service.AccountCreateService;
import com.kakao.cafe.account.service.AccountFindService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/user")
public class AccountController {
    private final AccountCreateService accountCreateService;
    private final AccountFindService accountFindService;

    public AccountController(AccountCreateService accountCreateService, AccountFindService accountFindService) {
        this.accountCreateService = accountCreateService;
        this.accountFindService = accountFindService;
    }

    @PostMapping
    public String saveAccount(@Valid @RequestBody AccountDto account) {
        accountCreateService.save(account);
        log.info("회원가입");
        return "index";
    }

    @GetMapping
    public String getUserList(Model model) {
        List<AccountDto> accountDtoList = accountFindService.getAccountList();
        model.addAttribute("users", accountDtoList);
        log.info("유저 리스트 조회");
        return "/user/list";
    }

    @GetMapping("/{userId}")
    public String getProfile(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("user_profile", accountFindService.getProfile(userId));
        log.info("유저 프로필 조회");
        return "user/profile";
    }

}
