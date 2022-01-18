package com.kakao.cafe.user.adapter.in.web;

import com.kakao.cafe.user.adapter.in.web.dto.request.UserAccountEnrollRequest;
import com.kakao.cafe.user.adapter.in.web.dto.response.UserAccountEnrollResponse;
import com.kakao.cafe.user.application.dto.result.UserAccountEnrollResult;
import com.kakao.cafe.user.application.port.in.EnrollUserAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

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
