package com.kakao.cafe.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    private final static String CLASSPATH_PREFIX = "classpath:";
    private final static String PATH_OF_STATIC = "static/";
    private final static String PATH_OF_TEMPLATES = "templates/";

    private String generateClassPath (String path) {
        return CLASSPATH_PREFIX + "/" + path;
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // resource 의 기본 접근 폴더는 static 으로 정의되어 있음
//        // 이를 해결하기 위한 오버라이딩
//        registry.addResourceHandler("/**")
//                .addResourceLocations(
//                        generateClassPath(PATH_OF_STATIC),
//                        generateClassPath(PATH_OF_TEMPLATES)
//                    );
//    }

//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        MustacheViewResolver resolver = new MustacheViewResolver();
//
//        resolver.setCharset("UTF-8");
//        resolver.setContentType("text/html;charset=UTF-8");
//        resolver.setPrefix(generateClassPath(PATH_OF_TEMPLATES));
//        resolver.setSuffix(".html");
//
//        registry.viewResolver(resolver);
//        WebMvcConfigurer.super.configureViewResolvers(registry);
//    }
}
