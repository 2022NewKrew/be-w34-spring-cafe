package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
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
    private final Logger logger = LoggerFactory.getLogger(JdbcUserRepository.class);


    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User save(User user) {
        logger.debug("[Jdbc] user save {}", user);
        String sql = "insert into users(user_id, password, name, email) values(?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql,
                    user.getUserId(), user.getPassword(), user.getUserName(), user.getEmail());
            logger.debug("[Jdbc] user save success: {}", user);
        } catch (DuplicateKeyException e) {
            throw new DuplicateUserException("사용자가 이미 존재합니다");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        logger.debug("[Jdbc] user findAll");
        String sql = "select * from users";

        List<User> userList = jdbcTemplate.query(sql, (rs, rowNum) -> new User(rs.getInt("id"),
                rs.getString("user_id"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email")));
        logger.debug("[Jdbc] user findAll success: {}", userList);
        return userList;
    }

    @Override
    public User findByUserId(String userId) {
        logger.debug("[Jdbc] user findByUserId userId: {}", userId);
        String sql = "select * from users where user_id = ?";

        try {
            User user = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new User(rs.getInt("id"),
                            rs.getString("user_id"),
                            rs.getString("password"),
                            rs.getString("name"),
                            rs.getString("email")),
                    userId);
            logger.debug("[Jdbc] user findByUserId success: {}", user);
            return user;
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("사용자 ID가 없습니다");
        }
    }

    @Override
    public User findById(int id) {
        logger.debug("[Jdbc] user findByid id: {}", id);
        String sql = "select * from users where id = ?";

        try {
            User user = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new User(rs.getInt("id"),
                            rs.getString("user_id"),
                            rs.getString("password"),
                            rs.getString("name"),
                            rs.getString("email")),
                    id);
            logger.debug("[Jdbc] user findByid success: {}", user);
            return user;
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("사용자 ID가 없습니다");
        }
    }
}
