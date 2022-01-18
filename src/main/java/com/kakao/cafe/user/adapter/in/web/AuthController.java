package com.kakao.cafe.user.adapter.in.web;

import com.kakao.cafe.user.adapter.in.web.dto.request.UserAccountLoginRequest;
import com.kakao.cafe.user.adapter.in.web.dto.response.UserAccountLoginResponse;
import com.kakao.cafe.user.application.dto.command.UserAccountCheckCommand;
import com.kakao.cafe.user.application.dto.result.UserAccountCheckResult;
import com.kakao.cafe.user.application.port.in.CheckUserAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CheckUserAccountUseCase checkUserAccountUseCase;

    @PostMapping("/login")
    public ResponseEntity<UserAccountLoginResponse> login(
            @Valid @RequestBody UserAccountLoginRequest request,
            HttpSession httpSession) {

        UserAccountCheckResult userAccountCheckResult = checkUserAccountUseCase.checkPassword(
                new UserAccountCheckCommand(
                        request.getEmail(),
                        request.getPassword()));

        if(userAccountCheckResult.isCorrect()) {
            httpSession.setAttribute("user-id", userAccountCheckResult.getUserAccountId());
            return ResponseEntity.ok()
                    .body(new UserAccountLoginResponse("success"));
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new UserAccountLoginResponse("unAuthorized"));
    }
}
