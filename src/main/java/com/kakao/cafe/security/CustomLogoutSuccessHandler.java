package com.kakao.cafe.security;

import com.kakao.cafe.constant.RedirectedURL;
import com.kakao.cafe.util.SessionUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        HttpSession session = request.getSession();
        SessionUtil.clearSession(session);

        response.sendRedirect(RedirectedURL.AFTER_LOGOUT);
    }

}
