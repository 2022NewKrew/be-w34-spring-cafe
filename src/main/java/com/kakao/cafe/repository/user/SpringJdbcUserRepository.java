package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class SpringJdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;

    @Override
    public void save(User user) {
        jdbcTemplate.update("insert into USERS(user_id,password,name,email) values (?,?,?,?)",
                user.getUserId(), user.getPassword(), user.getName(), user.getEmail());

    }

    @Override
    public Optional<User> findByUserId(String userId) {
        User user = jdbcTemplate.queryForObject("select * from USERS where user_id=?", userMapper, userId);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from USERS ", userMapper);
    }
}
