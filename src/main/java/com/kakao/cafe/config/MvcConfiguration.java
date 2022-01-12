package com.kakao.cafe.config;

import com.kakao.cafe.url.PostRedirect;
import com.kakao.cafe.url.PostView;
import com.kakao.cafe.url.UserView;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        // 시작페이지 조정
        registry.addRedirectViewController("/", "/posts/list");
    }
}
