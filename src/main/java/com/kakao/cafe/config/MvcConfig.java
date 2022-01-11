package com.kakao.cafe.config;

import com.kakao.cafe.dao.article.ArticleDao;
import com.kakao.cafe.dao.article.VolatilityArticleStorage;
import com.kakao.cafe.dao.user.UserDao;
import com.kakao.cafe.dao.user.VolatilityUserStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        registry.addRedirectViewController("/", "/index");
        registry.addViewController("/users/form").setViewName("/user/form");
        registry.addViewController("/users/login").setViewName("/user/login");
        registry.addViewController("/questions/form").setViewName("/qna/form");
    }

    @Bean
    public UserDao userDao() {
        return new VolatilityUserStorage();
    }

    @Bean
    public ArticleDao articleDao() {
        return new VolatilityArticleStorage();
    }
}
