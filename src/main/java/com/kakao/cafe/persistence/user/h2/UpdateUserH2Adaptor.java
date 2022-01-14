package com.kakao.cafe.persistence.user.h2;

import com.kakao.cafe.domain.user.UpdateUserPort;
import com.kakao.cafe.domain.user.User;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UpdateUserH2Adaptor implements UpdateUserPort {
    private static final String TABLE_NAME = "USER";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UpdateUserH2Adaptor(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        String UPDATE_USER = "UPDATE  " + TABLE_NAME + " SET USER_PASSWORD = :password, USER_NAME = :name, USER_EMAIL = :email" +
                " WHERE USER_ID = :userId";

        Map<String, Object> parameters = Map.of("userId", user.getUserId(),
                "password", user.getPassword(),
                "name", user.getName(),
                "email", user.getEmail());

        jdbcTemplate.update(UPDATE_USER, parameters);
    }
}
