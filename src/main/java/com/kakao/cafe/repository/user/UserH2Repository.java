package com.kakao.cafe.repository.user;

import com.kakao.cafe.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.kakao.cafe.constant.sql.UserSql.*;

@Repository
public class UserH2Repository implements UserRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final RowMapper<UserEntity> rowMapper;

    @Autowired
    UserH2Repository(DataSource dataSource, UserEntityRowMapper rowMapper) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
        this.rowMapper = rowMapper;
    }

    @Override
    public <S extends UserEntity> S save(S entity) {
        entity.initDate();
        SqlParameterSource params = new BeanPropertySqlParameterSource(entity);

        Long id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
        entity.putUserId(id);

        return entity;
    }

    @Override
    public UserEntity findOne(Long primaryKey) {
        Map<String, Long> params = Collections.singletonMap("primaryKey", primaryKey);

        return jdbcTemplate.queryForObject(FIND_BY_ID.getQuery(), params, rowMapper);
    }

    @Override
    public List<UserEntity> findAll() {
        return jdbcTemplate.query(FIND_ALL.getQuery(), rowMapper);
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public void delete(UserEntity entity) {

    }

    @Override
    public boolean exists(Long primaryKey) {
        return false;
    }

    @Override
    public UserEntity findByNickName(String nickName) {
        Map<String, String> params = Collections.singletonMap("nickName", nickName);

        return jdbcTemplate.queryForObject(FIND_BY_NICKNAME.getQuery(), params, rowMapper);
    }

    @Override
    public UserEntity findByEmail(String email) {
        Map<String, String> params = Collections.singletonMap("email", email);

        try {
            return jdbcTemplate.queryForObject(FIND_BY_EMAIL.getQuery(), params, rowMapper);
        } catch (Exception e) {
            throw e;
        }
    }
}
