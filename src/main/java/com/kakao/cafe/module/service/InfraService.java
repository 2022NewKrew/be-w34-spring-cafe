package com.kakao.cafe.module.service;

import com.kakao.cafe.infra.exception.ForbiddenException;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpSessionRequiredException;

import javax.servlet.http.HttpSession;

import java.util.Optional;

import static com.kakao.cafe.module.model.dto.UserDtos.*;

@Service
public class InfraService {

    public Long validateSession(HttpSession session, Long id) throws HttpSessionRequiredException {
        UserDto sessionUser = retrieveUserSession(session);
        if (!id.equals(sessionUser.getId())) {
            throw new ForbiddenException("권한 없는 정보입니다.");
        }
        return sessionUser.getId();
    }

    public UserDto retrieveUserSession(HttpSession session) throws HttpSessionRequiredException {
        return (UserDto) Optional.ofNullable(session.getAttribute("sessionUser"))
                .orElseThrow(() -> new HttpSessionRequiredException("로그인이 필요합니다"));
    }
}
