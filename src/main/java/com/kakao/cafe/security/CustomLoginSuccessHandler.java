package com.kakao.cafe.security;

import com.kakao.cafe.constant.RedirectedURL;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        HttpSession session = request.getSession();
        setUserIdInSessionFromAuthentication(session, authentication);
        String redirectedURL = getRedirectedURLFromRequestAndResponse(request, response);
        removeAttributesInSessionAfterLogin(session);

        response.sendRedirect(redirectedURL);
    }

    private void setUserIdInSessionFromAuthentication(HttpSession session, Authentication authentication) {
        String username = authentication.getName();
        Long userId = userService.findByEmail(username).getId();
        SessionUtil.setLoginUserId(session, userId);
    }

    private String getRedirectedURLFromRequestAndResponse(HttpServletRequest request, HttpServletResponse response) {
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String redirectUrlFromSavedRequest = savedRequest.getRedirectUrl();
            requestCache.removeRequest(request, response);
            return redirectUrlFromSavedRequest;
        }

        HttpSession session = request.getSession();
        String redirectedURLFromSession = SessionUtil.getAfterLoginRedirectedUrl(session);
        if (isRedirectedURLFromSessionNonNullAndNonEmpty(redirectedURLFromSession)) {
            return redirectedURLFromSession;
        }

        return RedirectedURL.AFTER_LOGIN_SUCCESS_DEFAULT;
    }

    private boolean isRedirectedURLFromSessionNonNullAndNonEmpty(String redirectedURLFromSession) {
        return redirectedURLFromSession != null && !redirectedURLFromSession.equals("");
    }

    private void removeAttributesInSessionAfterLogin(HttpSession session) {
        SessionUtil.removeAfterLoginRedirectedUrl(session);
        SessionUtil.removeAuthenticationExceptionAttributes(session);
    }
}
