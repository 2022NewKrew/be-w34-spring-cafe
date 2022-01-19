package com.kakao.cafe.repository;

import com.kakao.cafe.entity.User;
import com.kakao.cafe.exception.user.UserNotFoundException;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class UserDbRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserMapper mapper;

    public UserDbRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = new UserMapper();
    }

    @Override
    public void save(User entity) {
        String sql = "insert into user (email, username, password) values (?, ?, ?)";
        jdbcTemplate.update(sql, entity.getEmail(), entity.getUsername(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        String sql = "update user set username=?, password=? where email=?";
        jdbcTemplate.update(sql, entity.getUsername(), entity.getPassword(), entity.getEmail());
    }

    @Override
    public Optional<User> findByEmail(User entity) {
        String sql = "select email, username, password, reg_date, mod_date from user where email = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, mapper, entity.getEmail());
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.ofNullable(null);
        }
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        String sql = "select email, username, password, reg_date, mod_date from user ORDER BY reg_date DESC limit ? offset ?";
        int totalRow = jdbcTemplate.queryForObject("select count(*) from user", (rs, rowNum) -> rs.getInt(1));
        int totalPage = (int) Math.ceil(totalRow / (double) pageable.getSize());
        int fromIndex = pageable.getPage() * pageable.getSize();
        if (fromIndex >= totalRow)
            return new Page<User>(new ArrayList<>(), totalPage, totalRow, pageable);
        int toIndex = fromIndex + pageable.getSize();
        if (toIndex >= totalRow)
            toIndex = totalRow;

        List<User> users = jdbcTemplate.query(sql, mapper, toIndex - fromIndex, fromIndex);
        return new Page<User>(users, totalPage, totalRow, pageable);
    }

    private static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                return User.builder()
                        .email(rs.getString("email"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .regDate(rs.getTimestamp("reg_date").toLocalDateTime())
                        .modDate(rs.getTimestamp("mod_date").toLocalDateTime())
                        .build();
            } catch (SQLException e) {
                throw new UserNotFoundException();
            }
        }
    }

}
