package com.example.kakaocafe.core.config;

import com.example.kakaocafe.core.config.resolver.UpdateUserFormResolver;
import com.example.kakaocafe.core.config.resolver.WriteCommentFormResolver;
import com.example.kakaocafe.core.config.resolver.WritePostFormResolver;
import com.example.kakaocafe.core.meta.URLPath;
import com.example.kakaocafe.core.meta.ViewPath;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UpdateUserFormResolver());
        resolvers.add(new WritePostFormResolver());
        resolvers.add(new WriteCommentFormResolver());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        registry.addViewController(URLPath.SHOW_POST_FORM.getPath())
                .setViewName(ViewPath.WRITE_POST);
        registry.addViewController(URLPath.SHOW_ERROR_404.getPath())
                .setViewName(ViewPath.ERROR_404);
    }
}
