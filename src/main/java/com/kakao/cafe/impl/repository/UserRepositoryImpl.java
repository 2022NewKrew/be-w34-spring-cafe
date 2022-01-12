package com.kakao.cafe.impl.repository;

import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository("userRepository")
@Transactional
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long insertUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into USERTABLE (userid, password, email, time) values (?,?,?,now())", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            return ps;
        }, keyHolder);

        return (long) keyHolder.getKey();
    }

    @Override
    public User getUserById(long id) {
        return jdbcTemplate
                .queryForObject("select id, userid, password, email, to_char(time,'yyyy-MM-dd hh:mi') time from USERTABLE where id = ?",
                        (rs, rowNum) -> new User(
                                rs.getLong("id"),
                                rs.getString("userid"),
                                rs.getString("password"),
                                rs.getString("email"),
                                rs.getString("time")
                        ), id);
    }

    @Override
    public List<User> getAllUser() {
        return jdbcTemplate.query("select id, userid, password, email, to_char(time,'yyyy-MM-dd hh:mi') time from USERTABLE",
                (rs, rowNum) -> new User(
                        rs.getLong("id"),
                        rs.getString("userid"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("time")
                )
        );
    }

    @Override
    public int updateUser(User user) {
        return jdbcTemplate.update("update USERTABLE set email = ?,password = ? where id = ?",
                user.getEmail(),
                user.getPassword(),
                user.getId());

    }
}
