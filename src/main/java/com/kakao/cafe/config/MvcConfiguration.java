package com.kakao.cafe.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    private static final String USER_DIRECTORY = "/user";
    private static final String USER_VIEW_SIGN_IN_FAIL = USER_DIRECTORY+"/login_fail";
    private static final String USER_VIEW_SIGN_UP_FAIL = USER_DIRECTORY+"/form_fail";
    private static final String USER_VIEW_PROFILE_EDIT_FAIL = USER_DIRECTORY+"/profile_fail";

    private static final String POST_DIRECTORY = "/post";
    private static final String POST_VIEW_WRITE_FAIL = POST_DIRECTORY+"/form_fail";
    private static final String POST_VIEW_EDIT_FAIL = POST_DIRECTORY+"/form_edit_fail";

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        // 단순 화면 출력
        registry.addViewController("/users/sign-in/fail").setViewName(USER_VIEW_SIGN_IN_FAIL);
        registry.addViewController("/users/sign-up/fail").setViewName(USER_VIEW_SIGN_UP_FAIL);
        registry.addViewController("/users/profile/edit/fail").setViewName(USER_VIEW_PROFILE_EDIT_FAIL);
        registry.addViewController("/posts/write/fail").setViewName(POST_VIEW_WRITE_FAIL);
        registry.addViewController("/posts/edit/fail").setViewName(POST_VIEW_EDIT_FAIL);

        // 시작페이지 조정
        registry.addRedirectViewController("/", "/posts/list");
    }

//    @Bean
//    public DataSource h2DataSource() {
//        // jdbc:h2:tcp://localhost:3306/kakaodb
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .setName("kakaodb")
//                .addScript("classpath:/sql/schema.sql")
//                .addScript("classpath:/sql/data.sql").build();
//    }

    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    @Bean
    public DataSource mySqlDataSource() {
        return DataSourceBuilder.create().build();
    }
}
