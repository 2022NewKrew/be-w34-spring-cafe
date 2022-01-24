package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.util.SqlUser;
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

import static com.kakao.cafe.util.SqlUser.*;

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
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);

        if (findByEmail(user.getEmail()).isPresent()) {
            return update(user, namedParameters);
        }

        namedParameterJdbcTemplate.update(SqlUser.INSERT_USER.query(), namedParameters, keyHolder, new String[]{"id"});
        long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        user.setId(id);
        return user;
    }

    private User update(User user, SqlParameterSource namedParameters) {
        namedParameterJdbcTemplate.update(SqlUser.UPDATE_USER.query(), namedParameters);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return selectUserWhereCondition(FIND_USER_BY_ID.query(), namedParameters);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("email", email);
        return selectUserWhereCondition(FIND_USER_BY_EMAIL.query(), namedParameters);
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("nickname", nickname);
        return selectUserWhereCondition(FIND_USER_BY_NICKNAME.query(), namedParameters);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL_USER.query(), userRowMapper());
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
