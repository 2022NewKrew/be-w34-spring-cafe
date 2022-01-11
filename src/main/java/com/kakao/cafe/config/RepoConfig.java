package com.kakao.cafe.config;

import com.kakao.cafe.qna.ArticleRepository;
import com.kakao.cafe.qna.MemoryArticleRepository;
import com.kakao.cafe.user.MemoryUserRepository;
import com.kakao.cafe.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by melodist
 * Date: 2022-01-10 010
 * Time: 오후 1:55
 */
@Configuration
public class RepoConfig {

    @Bean
    public UserRepository userRepository() {
        return new MemoryUserRepository();
    }

    @Bean
    public ArticleRepository articleRepository() {
        return new MemoryArticleRepository();
    }
}
