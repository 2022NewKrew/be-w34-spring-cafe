package com.kakao.cafe.config;

import com.kakao.cafe.Interceptor.loginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/", "classpath:/static/")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
    }


    public void addInterceptors(InterceptorRegistry registry){
        //로그인체크
        registry.addInterceptor(new loginCheckInterceptor())
                .addPathPatterns("/qna/create")
                .addPathPatterns("/qna/form")
                .addPathPatterns("/qnas/*")
                .addPathPatterns("/qnas/update/*")
                .addPathPatterns("/qnas/delete/*")
                .addPathPatterns("/qnas/reply/create")
                .addPathPatterns("/user/update");
    }

}

