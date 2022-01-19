package com.kakao.cafe;

import com.kakao.cafe.article.adapter.out.JdbcTemplateArticleRepository;
import com.kakao.cafe.article.application.FindArticleService;
import com.kakao.cafe.article.application.WriteArticleService;
import com.kakao.cafe.article.application.port.in.FindArticleUseCase;
import com.kakao.cafe.article.application.port.in.WriteArticleUseCase;
import com.kakao.cafe.article.application.port.out.LoadArticlePort;
import com.kakao.cafe.article.application.port.out.SaveArticlePort;
import com.kakao.cafe.user.adapter.out.JdbcTemplateUserRepository;
import com.kakao.cafe.user.application.FindUserService;
import com.kakao.cafe.user.application.SignInService;
import com.kakao.cafe.user.application.SignUpService;
import com.kakao.cafe.user.application.port.in.FindUserUseCase;
import com.kakao.cafe.user.application.port.in.SignInUseCase;
import com.kakao.cafe.user.application.port.in.SignUpUseCase;
import com.kakao.cafe.user.application.port.out.LoadUserPort;
import com.kakao.cafe.user.application.port.out.SaveUserPort;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public FindUserUseCase findUserService() {
        return new FindUserService(loadUserPort());
    }

    @Bean
    public SignUpUseCase signUpService() {
        return new SignUpService(saveUserPort());
    }

    @Bean
    public SignInUseCase signInUseCase() {
        return new SignInService(loadUserPort());
    }

    @Bean
    public SaveUserPort saveUserPort() {
        return new JdbcTemplateUserRepository(dataSource);
    }

    @Bean
    public LoadUserPort loadUserPort() {
        return new JdbcTemplateUserRepository(dataSource);
    }

    @Bean
    public FindArticleUseCase findArticleQuery() {
        return new FindArticleService(loadArticlePort());
    }

    @Bean
    public WriteArticleUseCase writeArticleUseCase() {
        return new WriteArticleService(saveArticlePort());
    }

    @Bean
    public LoadArticlePort loadArticlePort() {
        return new JdbcTemplateArticleRepository(dataSource);
    }

    @Bean
    public SaveArticlePort saveArticlePort() {
        return new JdbcTemplateArticleRepository(dataSource);
    }
}
