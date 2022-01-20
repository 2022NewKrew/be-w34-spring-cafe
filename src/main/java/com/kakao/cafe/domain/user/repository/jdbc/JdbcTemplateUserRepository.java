package com.kakao.cafe.domain.user.repository.jdbc;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.repository.UserRepository;
import com.kakao.cafe.global.sql.Query;
import com.kakao.cafe.global.sql.TableName;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User save(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("users").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", user.getUserId());
        parameters.put("password", user.getPassword());
        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());
        parameters.put("joinDateTime", user.getJoinDateTime());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        user.setId(key.longValue());
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        List<User> result = jdbcTemplate.query("select * from " + TableName.USER +" where id = ? and isDeleted=false", userRowMapper(), id);
        return result.stream().findAny();
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            return User.builder()
                            .id(rs.getLong("id"))
                            .userId(rs.getString("userId"))
                            .password(rs.getString("password"))
                            .name(rs.getString("name"))
                            .email(rs.getString("email"))
                            .joinDateTime(rs.getTimestamp("joinDateTime").toLocalDateTime())
                            .build();
        };
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        List<User> result = jdbcTemplate.query("select * from " + TableName.USER +" where userId = ? and isDeleted=false", userRowMapper(), userId);
        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from " + TableName.USER + " where isDeleted=false", userRowMapper());
    }

    @Override
    public Optional<User> findByName(String name) {
        List<User> result = jdbcTemplate.query("select * from " + TableName.USER +" where name = ? and isDeleted=false", userRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public User update(User user) {
        jdbcTemplate.update("update " + TableName.USER +" set name=?, email=? where id=? and isDeleted=false", user.getName(), user.getEmail(), user.getId());
        return user;
    }

    @Override
    public boolean deleteById(Long id) {
        final String sql = "update " + TableName.USER + " set isDeleted=true where id=?";
        if (jdbcTemplate.update(sql, id) <= 0) { throw new RuntimeException("삭제에 실패하였습니다."); }
        return true;
    }
}
