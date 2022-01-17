package com.kakao.cafe.persistence.user.h2;

import com.kakao.cafe.domain.user.UpdateUserPort;
import com.kakao.cafe.domain.user.User;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;

public class UpdateUserH2Adaptor implements UpdateUserPort {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UpdateUserH2Adaptor(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        Map<String, Object> parameters = Map.of("user_id", user.getUserId(),
                "user_password", user.getPassword(),
                "user_name", user.getName(),
                "user_email", user.getEmail());

        jdbcTemplate.update(H2UserQueryBuilder.updateOne(
                List.of("user_password", "user_name", "user_email"), List.of("user_id")), parameters);
    }
}
