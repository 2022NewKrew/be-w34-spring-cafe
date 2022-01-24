package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;
    private final KeyHolder keyHolder;

    @Autowired
    public JdbcUserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
        keyHolder = new GeneratedKeyHolder();
    }

    @Override
    public User save(User user) {
        final String INSERT_USER = "INSERT INTO `USER`(email, nickname, password, createdAt) VALUES(:email, :nickname, :password, :createdAt)";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);

        if (findByEmail(user.getEmail()).isPresent()) {
            return update(user, namedParameters);
        }

        namedParameterJdbcTemplate.update(INSERT_USER, namedParameters, keyHolder, new String[]{"id"});
        long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        user.setId(id);
        return user;
    }

    private User update(User user, SqlParameterSource namedParameters) {
        final String UPDATE_USER = "UPDATE `USER` SET nickname=:nickname, password=:password WHERE id=:id";
        namedParameterJdbcTemplate.update(UPDATE_USER, namedParameters);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        final String FIND_USER_BY_ID = "SELECT * FROM `USER` WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return selectUserWhereCondition(FIND_USER_BY_ID, namedParameters);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        final String FIND_USER_BY_EMAIL = "SELECT * FROM `USER` WHERE email = :email";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("email", email);
        return selectUserWhereCondition(FIND_USER_BY_EMAIL, namedParameters);
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        final String FIND_USER_BY_NICKNAME = "SELECT * FROM `USER` WHERE nickname = :nickname";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("nickname", nickname);
        return selectUserWhereCondition(FIND_USER_BY_NICKNAME, namedParameters);
    }

    @Override
    public List<User> findAll() {
        final String FIND_ALL_USER = "SELECT * FROM `USER`";
        return jdbcTemplate.query(FIND_ALL_USER, userRowMapper());
    }

    private Optional<User> selectUserWhereCondition(String sql, SqlParameterSource namedParameters) {
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
                    sql, namedParameters, userRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) ->
                User.builder()
                        .id(rs.getLong("id"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .nickname(rs.getString("nickname"))
                        .createdAt(rs.getDate("createdAt").toLocalDate())
                        .build();
    }
}
