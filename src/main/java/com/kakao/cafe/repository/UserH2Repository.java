package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static com.kakao.cafe.query.user.H2RepositoryQuery.*;

@Primary
@Repository
public class UserH2Repository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserH2Repository(DataSource dataSource) {
        System.out.println("ㅋㅋㅋㅋㅋㅋㅋ");
        System.out.println(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        System.out.println("레포 에서");
        System.out.println(user);
        jdbcTemplate.update(insertQuery, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    @Override
    public List<User> getAllUser() {
        return jdbcTemplate
                .query(selectAllQuery, memberRowMapper());
    }

    @Override
    public User findById(String userId) {
        return jdbcTemplate
                .query(selectByIdQuery, memberRowMapper(), userId)
                .stream()
                .findAny()
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("존재하지 않는 Id 입니다");
                });
    }

    @Override
    public void deleteById(String userId) {
        jdbcTemplate.update(deleteByIdQuery, userId);
    }

    private RowMapper<User> memberRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUserId(rs.getString("userid"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            return user;
        };
    }
}
