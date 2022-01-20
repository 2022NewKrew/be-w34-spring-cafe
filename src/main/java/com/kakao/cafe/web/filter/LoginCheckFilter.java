package com.kakao.cafe.web.filter;

import com.kakao.cafe.web.meta.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whiteList = {"GET:/", "GET:/questions", "POST:/users", "POST:/login", "GET:/users/login", "GET:/users/form", "GET:/css/*", "GET:/*.ico"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestStr = toStringHttpMethodAndURI(httpServletRequest);
        log.info("login check filter : {}", requestStr);
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        try{
            if(isLoginCheckPath(requestStr)){
                HttpSession session = httpServletRequest.getSession(false);
                if(session == null || session.getAttribute(SessionConst.LOGIN_USER) == null){
                    //TODO 무한루프 범인잡음 ..
                    httpServletResponse.sendRedirect("/users/login");
                    return;
                }
            }
            chain.doFilter(request,response);
        }catch (Exception e){
            throw e;
        }finally {
        }
    }

    private boolean isLoginCheckPath(String requestStr){
        return !PatternMatchUtils.simpleMatch(whiteList, requestStr);
    }

    private String toStringHttpMethodAndURI(HttpServletRequest request){
        return String.format("%s:%s", request.getMethod(), request.getRequestURI());
    }
}
