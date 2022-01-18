package com.kakao.cafe.config;

import com.kakao.cafe.domain.auth.Auth;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        registry.addViewController("/user/login").setViewName("user/login");
        registry.addViewController("/user/form").setViewName("user/form");
        registry.addViewController("/article/form").setViewName("article/form");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor())
                .addPathPatterns("/users/**")
                .addPathPatterns("/articles/**")
                .excludePathPatterns("/users");
    }

    private static class AuthenticationInterceptor implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            Auth auth = (Auth) request.getSession().getAttribute("auth");
            if (auth == null) {
                response.sendRedirect("/user/login");
                return false;
            }
            return true;
        }
    }
}
