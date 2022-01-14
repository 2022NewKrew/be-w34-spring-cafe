package com.kakao.cafe.persistence.user.h2;

import com.kakao.cafe.domain.user.SignUpUserPort;
import com.kakao.cafe.domain.user.User;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SignUpUserH2Adaptor implements SignUpUserPort {
    private static final String TABLE_NAME = "USER";
    private static final List<String> FIELDS = List.of("user_id", "user_password", "user_name", "user_email");
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SignUpUserH2Adaptor(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        String INSERT_USER = "INSERT INTO " + TABLE_NAME + " ( " + String.join(", ", FIELDS) + " ) "
                + " VALUES (:userId, :password, :name, :email)";

        Map<String, Object> parameters = Map.of("userId", user.getUserId(),
                "password", user.getPassword(),
                "name", user.getName(),
                "email", user.getEmail());

        jdbcTemplate.update(INSERT_USER, parameters);
    }
}
