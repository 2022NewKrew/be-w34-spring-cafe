package com.kakao.cafe.api;

import com.kakao.cafe.api.dto.UserAccountEnrollRequest;
import com.kakao.cafe.api.dto.UserAccountEnrollResponse;
import com.kakao.cafe.application.user.UserAccountService;
import com.kakao.cafe.domain.user.UserAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserAccountApiController {

    private final UserAccountService userAccountService;

    public UserAccountApiController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping("")
    public ResponseEntity<UserAccountEnrollResponse> enroll(@RequestBody UserAccountEnrollRequest request) {

        UserAccount enroll = userAccountService.enroll(request.toCommand());

        return ResponseEntity.ok(new UserAccountEnrollResponse(enroll.getUserAccountId()));
    }
}
