package com.kakao.cafe.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kakao.cafe.controller.interceptor.AuthInfoCheckInterceptor;

@Configuration
@RequiredArgsConstructor
public class SpringConfig implements WebMvcConfigurer {
    private final AuthInfoCheckInterceptor authInfoCheckInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInfoCheckInterceptor)
                .excludePathPatterns("/js/*", "/css/*", "/fonts/*", "/*.ico");
    }

    //    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .setName("kakaodb")
//                .addScript("classpath:schema.sql")
//                .build();
//    }
}
