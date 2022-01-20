package com.kakao.cafe.interfaces.config;

import com.kakao.cafe.persistence.article.mysql.ArticleDaoMysqlAdaptor;
import com.kakao.cafe.persistence.article.mysql.ArticleTableDtoRowMapper;
import com.kakao.cafe.persistence.article.mysql.dto.ArticleEntityDtoMapper;
import com.kakao.cafe.persistence.user.UserRowMapper;
import com.kakao.cafe.persistence.user.h2.UserDaoH2Adaptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class AdaptorBeanConfig {

    @Bean
    UserDaoH2Adaptor userDaoPort(NamedParameterJdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        return new UserDaoH2Adaptor(jdbcTemplate, userRowMapper);
    }

    @Bean
    ArticleDaoMysqlAdaptor articleDaoPort(NamedParameterJdbcTemplate jdbcTemplate, ArticleTableDtoRowMapper articleRowMapper, ArticleEntityDtoMapper articleEntityDtoMapper) {
        return new ArticleDaoMysqlAdaptor(jdbcTemplate, articleRowMapper, articleEntityDtoMapper);
    }

}
