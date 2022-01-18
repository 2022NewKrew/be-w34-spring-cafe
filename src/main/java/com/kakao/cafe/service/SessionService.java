package com.kakao.cafe.service;

import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionService {

    public static final String SESSIONED_USER = "sessionedUser";

    public void setLoginUser(User user, HttpSession session) {
        session.setAttribute(SESSIONED_USER, user);
    }

    public User getLoginUser(HttpSession session) {
        Object loginUserObject = session.getAttribute(SESSIONED_USER);
        if(loginUserObject == null) {
            return null;
        }
        return (User)loginUserObject;
    }

}
