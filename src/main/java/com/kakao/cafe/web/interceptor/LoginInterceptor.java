package com.kakao.cafe.web.interceptor;

import com.kakao.cafe.member.dto.MemberResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    public List<String> loginPath = Arrays.asList("/articles/**", "/articleForm");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        MemberResponseDTO loginMemberDTO = (MemberResponseDTO) session.getAttribute("loginMemberDTO");

        if (loginMemberDTO != null) {
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }
}
