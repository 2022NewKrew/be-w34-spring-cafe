package com.kakao.cafe.interfaces.config;

import com.kakao.cafe.domain.user.FindUserPort;
import com.kakao.cafe.domain.user.SignUpUserPort;
import com.kakao.cafe.domain.user.UpdateUserPort;
import com.kakao.cafe.persistence.user.UserRowMapper;
import com.kakao.cafe.persistence.user.h2.FindUserH2Adaptor;
import com.kakao.cafe.persistence.user.h2.SignUpUserH2Adaptor;
import com.kakao.cafe.persistence.user.h2.UpdateUserH2Adaptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class AdaptorBeanConfig {

    @Bean
    public FindUserPort getFindUserAdaptor(NamedParameterJdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        return new FindUserH2Adaptor(jdbcTemplate, userRowMapper);
    }

    @Bean
    public SignUpUserPort getSignUpUserAdaptor(NamedParameterJdbcTemplate jdbcTemplate) {
        return new SignUpUserH2Adaptor(jdbcTemplate);
    }

    @Bean
    public UpdateUserPort getUpdateUserAdaptor(NamedParameterJdbcTemplate jdbcTemplate) {
        return new UpdateUserH2Adaptor(jdbcTemplate);
    }

}
