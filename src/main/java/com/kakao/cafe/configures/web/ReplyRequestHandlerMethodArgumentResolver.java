package com.kakao.cafe.configures.web;

import com.kakao.cafe.reply.ReplyRequest;
import com.kakao.cafe.users.UserDto;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ReplyRequestHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ReplyRequestResolver.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession();

        UserDto user = (UserDto)session.getAttribute("sessionedUser");
        long userSeq = user.getUserSeq();
        long articleSeq = Long.parseLong(webRequest.getParameter("articleSeq"));
        String writer = webRequest.getParameter("writer");
        String title = webRequest.getParameter("title");
        String contents = webRequest.getParameter("contents");

        return new ReplyRequest(userSeq, articleSeq, writer, title, contents);
    }
}
