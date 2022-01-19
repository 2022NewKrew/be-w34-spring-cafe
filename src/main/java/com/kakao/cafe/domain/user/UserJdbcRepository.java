package com.kakao.cafe.domain.user;

import com.kakao.cafe.core.DBConst;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserJdbcRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("insert into " + DBConst.USER_DB + " (userId, email, name, password) values(?,?,?,?)", user.getUserId(), user.getEmail(), user.getName(), user.getPassword());
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from " + DBConst.USER_DB, userRowMapper());
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        List<User> query = jdbcTemplate.query("select * from " + DBConst.USER_DB + " where userId=?", userRowMapper(), userId);

        return query.stream().findAny();
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("update " + DBConst.USER_DB + " set name=?, email=? where id = ?", user.getName(), user.getEmail(), user.getId());
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUserId(rs.getString("userId"));
            user.setEmail(rs.getString("email"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            return user;
        };
    }
}
