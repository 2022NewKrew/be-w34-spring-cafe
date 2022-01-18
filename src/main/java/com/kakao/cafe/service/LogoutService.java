package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class LogoutService {
    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    public void logout(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("sessionedUser");
        logger.info("{} logout success", user.getUserId());
        httpSession.removeAttribute("sessionedUser");
        logger.info("{} session removed", user.getUserId());
    }
}
