package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Primary
@Repository
public class UserH2Repository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserH2Repository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        String sql = "insert into user (userid, password, name, email) values (?,?,?,?)";
        jdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    @Override
    public List<User> getAllUser() {
        String sql = "select * from user";
        return jdbcTemplate
                .query(sql, memberRowMapper());
    }

    @Override
    public User findById(String userId) {
        String sql = "select * from user where userid = ?";
        return jdbcTemplate
                .query(sql, memberRowMapper(), userId)
                .stream()
                .findAny()
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("존재하지 않는 Id 입니다");
                });
    }

    @Override
    public void deleteById(String userId) {
        String sql = "delete from user where userid = ?";
        jdbcTemplate.update(sql, userId);
    }

    private RowMapper<User> memberRowMapper() {
        return new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUserId(rs.getString("userid"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        };
    }
}
