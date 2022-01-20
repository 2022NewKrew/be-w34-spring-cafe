package com.kakao.cafe.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = String.format("%s:%s", httpServletRequest.getMethod(), httpServletRequest.getRequestURI());

        String uuid = UUID.randomUUID().toString();

        try{
            log.info("REQUEST [{}][{}]", uuid, requestURI);
            chain.doFilter(request, response);
        }
        catch (Exception e){
            throw e;
        }
        finally {
//            log.info("RESPONSE [{}][{}]", uuid, requestURI);
        }
    }
}
