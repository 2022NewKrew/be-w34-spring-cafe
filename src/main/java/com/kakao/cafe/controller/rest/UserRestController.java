package com.kakao.cafe.controller.rest;

import com.kakao.cafe.controller.RedirectedURL;
import com.kakao.cafe.dto.UserJoinDto;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/users/form")
    public void join(UserJoinDto userJoinDto, HttpServletResponse response) throws IOException {
        String redirectedURL = RedirectedURL.AFTER_JOIN;
        encodePassword(userJoinDto);
        userService.join(userJoinDto);

        response.sendRedirect(redirectedURL);
    }

    private void encodePassword(UserJoinDto userJoinDto) {
        userJoinDto.setPassword(passwordEncoder.encode(userJoinDto.getPassword()));
    }
}
