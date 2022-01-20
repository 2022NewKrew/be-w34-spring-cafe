package com.kakao.cafe.user.repository;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kakao.cafe.user.entity.User;
import com.kakao.cafe.user.entity.mapper.UserRowMapper;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private static final String SAVE_QUERY = "INSERT INTO `user`(user_id, password, name, email) VALUES(?, ?, ?, ?)";
    private static final String FIND_ALL_QUERY = "SELECT * FROM `user`";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM `user` WHERE id = ?";
    private static final String FIND_BY_USER_ID_QUERY = "SELECT * FROM `user` WHERE user_id = ?";
    private static final String UPDATE_QUERY = "UPDATE `user` SET password = ?, name = ?, email = ? WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    @Override
    public void save(User user) {
        jdbcTemplate.update(SAVE_QUERY, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    @Override
    public List<User> findAll() {
        List<User> userList = jdbcTemplate.query(FIND_ALL_QUERY, userRowMapper);

        return userList;
    }

    @Override
    public Optional<User> findById(int id) {
        List<User> user = jdbcTemplate.query(FIND_BY_ID_QUERY, userRowMapper, id);

        return user.stream().findFirst();
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        List<User> user = jdbcTemplate.query(FIND_BY_USER_ID_QUERY, userRowMapper, userId);

        return user.stream().findFirst();
    }

    @Override
    public void update(int id, User user) {
        jdbcTemplate.update(UPDATE_QUERY, user.getPassword(), user.getName(), user.getEmail(), id);
    }
}
