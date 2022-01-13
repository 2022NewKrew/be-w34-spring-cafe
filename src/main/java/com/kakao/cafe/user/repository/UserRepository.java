package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    public static final String SELECT_USER_ALL = "SELECT id, user_id, password, name, email FROM TB_USER user";
    public static final String SELECT_USER_BY_USERID = "SELECT * FROM TB_USER WHERE user_id = ?";
    public static final String INSERT_USER = "INSERT INTO TB_USER(user_id, password, name, email) VALUES(?, ?, ?, ?)";
    public static final String UPDATE_USER = "UPDATE TB_USER SET password = ?, name = ?, email = ? where user_id = ?";

    private final JdbcTemplate jdbcTemplate;

    public int save(UserEntity entity) {
        if (entity.getId() == null) {
            return create(entity);
        }
        return update(entity);
    }

    public List<UserEntity> findAll() {
        return jdbcTemplate.query(SELECT_USER_ALL, this::convertUser);
    }

    public Optional<UserEntity> findByUserId(String userId) {
        UserEntity userEntity = jdbcTemplate.queryForObject(SELECT_USER_BY_USERID, this::convertUser, userId);
        return Optional.ofNullable(userEntity);
    }

    private int create(UserEntity userEntity) {
        return jdbcTemplate.update(INSERT_USER, userEntity.getUserId(), userEntity.getPassword(), userEntity.getName(), userEntity.getEmail());
    }

    private int update(UserEntity userEntity) {
        return jdbcTemplate.update(UPDATE_USER, userEntity.getPassword(), userEntity.getName(), userEntity.getEmail(), userEntity.getUserId());
    }

    private UserEntity convertUser(ResultSet rs, int rowNum) throws SQLException {
        return UserEntity.builder()
                .id(rs.getLong("id"))
                .userId(rs.getString("user_id"))
                .password(rs.getString("password"))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .build();
    }
}
