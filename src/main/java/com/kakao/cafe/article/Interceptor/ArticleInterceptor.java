package com.kakao.cafe.article.Interceptor;

import com.kakao.cafe.article.exception.ArticleNotLoggedInException;
import com.kakao.cafe.user.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArticleInterceptor implements HandlerInterceptor {

    //로그인체크를 진행
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        User loggedInuser;
        if((loggedInuser = (User) request.getSession().getAttribute("sessionedUser")) == null){
            throw new ArticleNotLoggedInException("로그인 상태가 아닙니다.");
        }

        return true;
    }

}
