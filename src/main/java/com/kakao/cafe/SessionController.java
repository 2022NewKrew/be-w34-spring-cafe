package com.kakao.cafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class SessionController {
    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    public static Optional<String> checkSession(HttpSession session, List<User> users) {
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            logger.info("Session is null");
            session.setAttribute("errormsg", "세션이 만료되었습니다.");
            return Optional.of("redirect:/user/error");
        }
        User user = (User)value;
        Optional<User> matchedUser = users.stream().filter(x ->
                        x.getUserId().equals(user.getUserId()) &&
                                x.getPassword().equals(user.getPassword())).findFirst();
        if(matchedUser.isEmpty()) {
            logger.info("Wrong userId or password");
            session.setAttribute("errormsg", "사용자 아이디 혹은 비밀번호가 일치하지 않습니다.");
            return Optional.of("redirect:/user/error");
        }
        return Optional.empty();
    }
}
