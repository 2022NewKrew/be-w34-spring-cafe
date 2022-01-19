package com.kakao.cafe.controller.rest;

import com.kakao.cafe.constant.RedirectedURL;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.user.ProfileDto;
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

    @PostMapping("/join")
    public void join(UserJoinDto userJoinDto, HttpServletResponse response) throws IOException {
        userJoinDto.setPassword(encodePassword(userJoinDto.getPassword()));
        User user = userService.join(userJoinDto);

        String redirectedURL = RedirectedURL.AFTER_JOIN + "/" + user.getId();
        response.sendRedirect(redirectedURL);
    }

    @PutMapping("/users")
    public void updateProfile(ProfileDto profileDto, HttpServletResponse response) throws IOException {
        profileDto.setPassword(encodePassword(profileDto.getPassword()));
        userService.updateProfile(profileDto);

        response.sendRedirect(RedirectedURL.AFTER_UPDATE_PROFILE);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
