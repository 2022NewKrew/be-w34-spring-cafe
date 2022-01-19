package com.kakao.cafe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        // UserController
        registry.addViewController("/signup").setViewName("users/signup");
        registry.addViewController("/dupUserFound").setViewName("users/dupIdFound");
        registry.addViewController("/editUserFailed").setViewName("users/editFailed");

        // ArticleController
        registry.addViewController("/editArticleFailed").setViewName("articles/editFailed");
        registry.addViewController("/editArticleFailedNoPerm").setViewName("articles/editFailedNoPerm");

        // CommentController
        registry.addViewController("/editCommentFailedNoPerm").setViewName("comments/editFailedNoPerm");
    }
}
