package com.kakao.cafe.common.config;

import com.kakao.cafe.article.mapper.ArticleMapper;
import com.kakao.cafe.user.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ArticleMapper articleMapper() {
        return ArticleMapper.INSTANCE;
    }

    @Bean
    public UserMapper userMapper() {
        return UserMapper.INSTANCE;
    }
}
