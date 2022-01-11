package com.kakao.cafe.controller.rest;

import com.kakao.cafe.constant.RedirectedURL;
import com.kakao.cafe.dto.user.ProfileUpdateDto;
import com.kakao.cafe.dto.user.UserJoinDto;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/users/join")
    public void join(UserJoinDto userJoinDto, HttpServletResponse response) throws IOException {
        String redirectedURL = RedirectedURL.AFTER_JOIN;
        userJoinDto.setPassword(encodePassword(userJoinDto.getPassword()));
        userService.join(userJoinDto);

        response.sendRedirect(redirectedURL);
    }

    @PutMapping("/users/update")
    public void updateProfile(ProfileUpdateDto profileUpdateDto, HttpServletResponse response) throws IOException {
        String redirectedURL = RedirectedURL.AFTER_UPDATE_PROFILE;
        profileUpdateDto.setPassword(encodePassword(profileUpdateDto.getPassword()));
        userService.updateProfile(profileUpdateDto);

        response.sendRedirect(redirectedURL);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
