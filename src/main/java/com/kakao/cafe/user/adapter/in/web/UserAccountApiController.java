package com.kakao.cafe.user.adapter.in.web;

import com.kakao.cafe.user.application.UserAccountService;
import com.kakao.cafe.user.domain.UserAccount;
import com.kakao.cafe.user.adapter.in.web.dto.UserAccountEnrollRequest;
import com.kakao.cafe.user.adapter.in.web.dto.UserAccountLoginRequest;
import com.kakao.cafe.user.adapter.in.web.dto.UserAccountEnrollResponse;
import com.kakao.cafe.user.adapter.in.web.dto.UserAccountLoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAccountApiController {

    private final UserAccountService userAccountService;

    @PostMapping("")
    public ResponseEntity<UserAccountEnrollResponse> enroll(@Valid @RequestBody UserAccountEnrollRequest request) {

        UserAccount enroll = userAccountService.enroll(request.toCommand());

        UriComponents uriComponents = MvcUriComponentsBuilder.fromMethodCall(on(UserAccountApiController.class).enroll(request)).build();

        return ResponseEntity.created(uriComponents.toUri())
                .body(new UserAccountEnrollResponse(enroll.getUserAccountId()));
    }

    @PostMapping("/login")
    public ResponseEntity<UserAccountLoginResponse> login(@Valid @RequestBody UserAccountLoginRequest request, HttpServletRequest servletRequest) {
        UserAccount user = userAccountService.getUserInfoByEmail(request.getEmail());
        if(user.getPassword().equals(request.getPassword())) {
            servletRequest.getSession().setAttribute("user-id", user.getUserAccountId());
            return ResponseEntity.ok()
                    .body(new UserAccountLoginResponse("success"));
        }

        return new ResponseEntity<>(new UserAccountLoginResponse("unauthorized"), HttpStatus.UNAUTHORIZED);
    }
}
