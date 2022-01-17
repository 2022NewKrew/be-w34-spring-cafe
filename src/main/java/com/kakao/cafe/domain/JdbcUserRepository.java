package com.kakao.cafe.domain;

import com.kakao.cafe.exceptions.DuplicateUserException;
import com.kakao.cafe.exceptions.UserNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(JdbcUserRepository.class);


    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User save(User user) {
        logger.info("[Jdbc] user save");
        String sql = "insert into users(user_id, password, name, email) values(?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql,
                    user.getUserId(), user.getPassword(), user.getUserName(), user.getEmail());
        } catch (DuplicateKeyException e) {
            throw new DuplicateUserException("사용자가 이미 존재합니다");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        logger.info("[Jdbc] user findAll");
        String sql = "select * from users";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new User(rs.getString("id"),
                rs.getString("user_id"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email")));
    }

    @Override
    public User findByUserId(String id) {
        logger.info("[Jdbc] user findByUserId");
        String sql = "select * from users where user_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new User(rs.getString("id"),
                            rs.getString("user_id"),
                            rs.getString("password"),
                            rs.getString("name"),
                            rs.getString("email")),
                            id);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("사용자 ID가 없습니다");
        }
    }
}
