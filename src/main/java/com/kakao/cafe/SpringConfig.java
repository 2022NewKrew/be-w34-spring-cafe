package com.kakao.cafe;

import com.kakao.cafe.article.adapter.out.MemoryArticleRepository;
import com.kakao.cafe.article.application.FindArticleService;
import com.kakao.cafe.article.application.WriteArticleService;
import com.kakao.cafe.article.application.port.in.FindArticleQuery;
import com.kakao.cafe.article.application.port.in.WriteArticleUseCase;
import com.kakao.cafe.article.application.port.out.LoadArticlePort;
import com.kakao.cafe.article.application.port.out.SaveArticlePort;
import com.kakao.cafe.user.adapter.out.MemoryUserRepository;
import com.kakao.cafe.user.application.FindUserService;
import com.kakao.cafe.user.application.SignUpService;
import com.kakao.cafe.user.application.port.out.LoadUserPort;
import com.kakao.cafe.user.application.port.out.SaveUserPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public FindUserService findUserService() {
        return new FindUserService(loadUserPort());
    }

    @Bean
    public SignUpService signUpService() {
        return new SignUpService(saveUserPort());
    }

    @Bean
    public SaveUserPort saveUserPort() {
        return new MemoryUserRepository();
    }

    @Bean
    public LoadUserPort loadUserPort() {
        return new MemoryUserRepository();
    }

    @Bean
    public FindArticleQuery findArticleQuery() {
        return new FindArticleService(loadArticlePort());
    }

    @Bean
    public WriteArticleUseCase writeArticleUseCase() {
        return new WriteArticleService(saveArticlePort());
    }

    @Bean
    public LoadArticlePort loadArticlePort() {
        return new MemoryArticleRepository();
    }

    @Bean
    public SaveArticlePort saveArticlePort() {
        return new MemoryArticleRepository();
    }
}
