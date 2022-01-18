package com.kakao.cafe.user.adapter.in.web;

import com.kakao.cafe.user.adapter.in.web.dto.request.UserAccountEnrollRequest;
import com.kakao.cafe.user.adapter.in.web.dto.request.UserAccountLoginRequest;
import com.kakao.cafe.user.adapter.in.web.dto.response.UserAccountEnrollResponse;
import com.kakao.cafe.user.adapter.in.web.dto.response.UserAccountLoginResponse;
import com.kakao.cafe.user.application.dto.command.UserAccountCheckCommand;
import com.kakao.cafe.user.application.dto.result.UserAccountCheckResult;
import com.kakao.cafe.user.application.dto.result.UserAccountDetailResult;
import com.kakao.cafe.user.application.dto.result.UserAccountEnrollResult;
import com.kakao.cafe.user.application.port.in.CheckUserAccountUseCase;
import com.kakao.cafe.user.application.port.in.EnrollUserAccountUseCase;
import com.kakao.cafe.user.application.port.in.GetUserAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAccountApiController {

    private final EnrollUserAccountUseCase enrollUserAccountUseCase;

    @PostMapping("")
    public ResponseEntity<UserAccountEnrollResponse> enroll(@Valid @RequestBody UserAccountEnrollRequest request) {

        UserAccountEnrollResult enroll = enrollUserAccountUseCase.enroll(request.toCommand());

        UriComponents uriComponents = MvcUriComponentsBuilder.fromMethodCall(on(UserAccountApiController.class).enroll(request)).build();

        return ResponseEntity.created(uriComponents.toUri())
                .body(enroll.toResponse());
    }
}
