package com.kakao.cafe.util;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class PageArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Page.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Page page = parameter.getParameterAnnotation(Page.class);
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        int limit = page.limit();
        int offset = page.offset();
        if(hasIntegerParameter(request, "limit")) {
            limit = getIntegerParameter(request, "limit");
        }
        if(hasIntegerParameter(request, "offset")) {
            offset = getIntegerParameter(request, "offset");
        }
        return new PagingRequest(limit, offset);
    }

    private int getIntegerParameter(HttpServletRequest request, String parameter) {
        return Integer.parseInt(request.getParameter(parameter));
    }

    private boolean hasIntegerParameter(HttpServletRequest request, String parameter) {
        String param = request.getParameter(parameter);
        if(param == null) {
            return false;
        }
        try {
            Integer.parseInt(param);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
