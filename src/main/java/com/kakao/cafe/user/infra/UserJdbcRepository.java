package com.kakao.cafe.user.infra;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserJdbcRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(User user) {
        String query = "insert into users(user_id, password, username, email) " +
                "values(?, ?, ?, ?)";

        return jdbcTemplate.update(
                query,
                user.getUserId(),
                user.getPassword(),
                user.getName(),
                user.getEmail()
        );
    }

    @Override
    public List<User> findAll() {
        String query = "select * from users";
        return jdbcTemplate.query(query, mapUserRow());
    }

    @Override
    public User findByIdOrNull(String userId) {
        String query = "select * from users where user_id = ?";
        try {
            return jdbcTemplate.queryForObject(query, mapUserRow(), userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean existsById(String userId) {
        return findByIdOrNull(userId) != null;
    }

    @Override
    public void delete(User user) {
        String userId = user.getUserId();
        String query = "delete from users where user_id = ?";
        jdbcTemplate.update(query, userId);
    }

    private RowMapper<User> mapUserRow() {
        return (rs, rowNum) -> User.valueOf(
                rs.getString("user_id"),
                rs.getString("password"),
                rs.getString("username"),
                rs.getString("email")
        );
    }
}
