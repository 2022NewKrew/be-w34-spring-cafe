package com.kakao.cafe.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class DbRepository {

    Logger logger = LoggerFactory.getLogger(DbRepository.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public User saveUser(User user) {
        final String insertQuery = "insert into `user` (email, nickname, point) values (?, ?, ?)";
        final String selectIdQuery = "select id from `user` where email = :email";

        try {
            jdbcTemplate.update(insertQuery, user.getEmail(), user.getNickname(), user.getPoint());
        } catch (DataAccessException e) {
            logger.info("사용자 아이디 중복: {}", user);
            return user;
        }

        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
        Long id = namedParameterJdbcTemplate.queryForObject(selectIdQuery, namedParameters, Long.class);
        user.setId(id);
        logger.info("save user: {}", user);
        return user;
    }

    public User findUserByEmail(String email) {
        final String sql = "select id, email, nickname, point from `user` where email= :email";
        User user = new User();
        user.setEmail(email);
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
        try {
            return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, (rs, rowNum) -> {
                User u = new User();
                u.setId(rs.getLong("id"));
                u.setEmail(rs.getString("email"));
                u.setNickname(rs.getString("nickname"));
                u.setPoint(rs.getInt("point"));
                return u;
            });
        } catch (DataAccessException e) {
            logger.info("findUserByEmail Exception: {}", e.getMessage());
            return null;
        }
    }
}
