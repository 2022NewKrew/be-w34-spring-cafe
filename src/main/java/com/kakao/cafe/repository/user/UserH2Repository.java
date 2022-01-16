package com.kakao.cafe.repository.user;

import com.kakao.cafe.entity.ArticleEntity;
import com.kakao.cafe.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class UserH2Repository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    UserH2Repository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public <S extends UserEntity> S save(S entity) {
        try {
            entity.putCreatedDate();
            entity.putUpdatedDate();

            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("email", entity.getEmail())
                    .addValue("nickname", entity.getNickName())
                    .addValue("password", entity.getPassword())
                    .addValue("registered_date", Timestamp.valueOf(entity.getRegisteredDate()))
                    .addValue("created_date", Timestamp.valueOf(entity.getCreatedDate()))
                    .addValue("updated_date", Timestamp.valueOf(entity.getUpdatedDate()));

            Long id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
            entity.putUserId(id);

            return entity;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public UserEntity findOne(Long primaryKey) {
        String sql = "select * from users where id = " + primaryKey;

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new UserEntity(rs.getLong("id"), rs.getString("email"),
                        rs.getString("nickname"), rs.getString("password"),
                        rs.getTimestamp("registered_date").toLocalDateTime())).get(0);
    }

    @Override
    public List<UserEntity> findAll() {
        String sql = "select * from users";

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new UserEntity(rs.getLong("id"), rs.getString("email"),
                        rs.getString("nickname"), rs.getString("password"),
                        rs.getTimestamp("registered_date").toLocalDateTime()));
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
        return null;
    }
}
