package com.kakao.cafe.config;

import com.kakao.cafe.url.PostView;
import com.kakao.cafe.url.UserView;
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

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        // 단순 화면 출력
        registry.addViewController("/users/sign-up/fail").setViewName(UserView.USER_VIEW_SIGN_UP_FAIL);
        registry.addViewController("/posts/write/fail").setViewName(PostView.POST_VIEW_WRITE_FAIL);

        // 시작페이지 조정
        registry.addRedirectViewController("/", "/posts/list");
    }

    @Bean
    public DataSource myDataSource() {
        // jdbc:h2:tcp://localhost:3306/kakaodb
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("kakaodb")
                .addScript("classpath:/sql/schema.sql")
                .addScript("classpath:/sql/data.sql").build();
    }
}
