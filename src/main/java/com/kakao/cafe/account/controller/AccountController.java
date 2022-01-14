package com.kakao.cafe.account.controller;

import com.kakao.cafe.account.dto.AccountDto;
import com.kakao.cafe.account.service.AccountCreateService;
import com.kakao.cafe.account.service.AccountFindService;
import com.kakao.cafe.exception.ErrorCode;
import com.kakao.cafe.exception.ErrorResponse;
import com.kakao.cafe.exception.custom.DuplicateException;
import com.kakao.cafe.exception.custom.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
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
        System.out.println(account.getEmail());
        accountCreateService.save(account);
        return "redirect:user/list";
    }

    @GetMapping
    public String getUserList(Model model) {
        List<AccountDto> accountDtoList = accountFindService.getAccountList();
        model.addAttribute("users", accountDtoList);
        return "/user/list";
    }

    @GetMapping("/{userId}")
    public String getProfile(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("user_profile", accountFindService.getProfile(userId));
        return "user/profile";
    }

    // account 에러 처리
    @ExceptionHandler(DuplicateException.class)
    protected ResponseEntity<ErrorResponse> handleDuplicateException(Exception e) {
        log.error("handleDuplicateException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.DUPLICATED_USER_ID);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.DUPLICATED_USER_ID.getStatus()));
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundException(Exception e) {
        log.error("handleNotFoundException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.NOT_FOUND_USER_ID);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.NOT_FOUND_USER_ID.getStatus()));
    }
}
