package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements MyRepository<User, Long> {

    private final JdbcTemplate jdbcTemplate;
    private final UserMapper mapper;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = new UserMapper();
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "select u.id, u.username, u.nickname, u.email, u.password " +
                "from users u " +
                "where u.id = ?";

        try {
            User user = jdbcTemplate.queryForObject(sql, mapper, id);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "select u.id, u.username, u.nickname, u.email, u.password " +
                "from users u";

        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public void save(User entity) {
        String sql = "insert into users (username, nickname, email, password) values ( ?, ?, ?, ? )";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getNickname());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getPassword());
            return ps;
        }, keyHolder);
    }

    @Override
    public void update(User entity) {
        String sql = "update users " +
                "set nickname = ?, email = ?, password = ? " +
                "where id = ?";

        jdbcTemplate.update(
                sql,
                entity.getNickname(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getId()
        );
    }

    private static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("nickname"),
                    rs.getString("email"),
                    rs.getString("password")
            );
        }
    }
}
