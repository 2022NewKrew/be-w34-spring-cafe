package com.kakao.cafe.interfaces.config;

import com.kakao.cafe.domain.article.ArticleDaoPort;
import com.kakao.cafe.domain.user.UserDaoPort;
import com.kakao.cafe.persistence.article.ArticleRowMapper;
import com.kakao.cafe.persistence.article.h2.ArticleDaoH2Adaptor;
import com.kakao.cafe.persistence.user.UserRowMapper;
import com.kakao.cafe.persistence.user.h2.UserDaoH2Adaptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class AdaptorBeanConfig {

    @Bean
    UserDaoPort userDaoPort(NamedParameterJdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        return new UserDaoH2Adaptor(jdbcTemplate, userRowMapper);
    }

    @Bean
    ArticleDaoPort articleDaoPort(NamedParameterJdbcTemplate jdbcTemplate, ArticleRowMapper articleRowMapper) {
        return new ArticleDaoH2Adaptor(jdbcTemplate, articleRowMapper);
    }

}
