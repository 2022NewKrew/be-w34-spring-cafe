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
        registry.addViewController("/dupIdFound").setViewName("users/dupIdFound");
        registry.addViewController("/editUserFailed").setViewName("users/editFailed");

        // ArticleController
        registry.addViewController("/").setViewName("redirect:/pages/1");
        registry.addViewController("/articles").setViewName("redirect:/pages/1");
        registry.addViewController("/editArticleFailedNoPerm").setViewName("articles/editFailedNoPerm");
        registry.addViewController("/delArticleFailedOthersCommentExist").setViewName("articles/delFailedOthersCommentExist");

        // CommentController
        registry.addViewController("/editCommentFailedNoPerm").setViewName("comments/editFailedNoPerm");
    }
}
