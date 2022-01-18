package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.util.Sql;
import com.kakao.cafe.util.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User save(User user) {
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);

        if (findByEmail(user.getEmail()).isPresent()) {
            return update(user, namedParameters);
        }

        int id = namedParameterJdbcTemplate.update(Sql.INSERT_USER, namedParameters);
        user.setId(id);
        return user;
    }

    private User update(User user, SqlParameterSource namedParameters) {
        namedParameterJdbcTemplate.update(Sql.UPDATE_USER, namedParameters);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return selectUserWhereCondition(Sql.FIND_USER_BY_ID, namedParameters);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("email", email);
        return selectUserWhereCondition(Sql.FIND_USER_BY_EMAIL, namedParameters);
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("nickname", nickname);
        return selectUserWhereCondition(Sql.FIND_USER_BY_NICKNAME, namedParameters);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(Sql.FIND_ALL_USER, new UserRowMapper());
    }

    private Optional<User> selectUserWhereCondition(String sql, SqlParameterSource namedParameters) {
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
                    sql, namedParameters, new UserRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
