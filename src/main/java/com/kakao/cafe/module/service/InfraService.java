package com.kakao.cafe.module.service;

import org.springframework.stereotype.Service;
import org.springframework.web.HttpSessionRequiredException;

import javax.servlet.http.HttpSession;

import java.util.Optional;

import static com.kakao.cafe.module.model.dto.UserDtos.*;

@Service
public class InfraService {

    public UserDto retrieveUserSession(HttpSession session) throws HttpSessionRequiredException {
        return (UserDto) Optional.ofNullable(session.getAttribute("sessionUser"))
                .orElseThrow(() -> new HttpSessionRequiredException("로그인이 필요합니다"));
    }
}
