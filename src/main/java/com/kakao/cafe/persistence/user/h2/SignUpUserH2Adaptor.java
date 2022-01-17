package com.kakao.cafe.persistence.user.h2;

import com.kakao.cafe.domain.user.SignUpUserPort;
import com.kakao.cafe.domain.user.User;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;

public class SignUpUserH2Adaptor implements SignUpUserPort {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SignUpUserH2Adaptor(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        Map<String, Object> parameters = Map.of("user_id", user.getUserId(),
                "user_password", user.getPassword(),
                "user_name", user.getName(),
                "user_email", user.getEmail());

        jdbcTemplate.update(H2UserQueryBuilder.insertOne(List.of("user_id", "user_password", "user_name", "user_email")), parameters);
    }
}
