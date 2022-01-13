package com.kakao.cafe.user;


import com.kakao.cafe.user.mapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

/**
 * UserRepository H2 데이터베이스 구현체입니다.
 * Spring Jdbc Template 를 사용합니다.
 *
 * @author jm.hong
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryH2 implements UserRepository {

    private static final int FAIL = 0;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long save(User user) throws SQLException {

        String sql = "INSERT INTO member(user_id, password, name, email) values (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rs = jdbcTemplate.update(
                con -> {
                    PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, user.getUserId());
                    ps.setString(2, user.getPassword());
                    ps.setString(3, user.getName());
                    ps.setString(4, user.getEmail());

                    log.debug(ps.toString());

                    return ps;
                }, keyHolder);

        if (rs == FAIL) {
            throw new SQLException("USER TABLE SAVE FAIL");
        }

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public User findOne(Long id) {

        String sql = "SELECT id, user_id, password, name, email, role FROM member WHERE id=?";

        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);

    }

    @Override
    public List<User> findAll() {

        String sql = "SELECT id, user_id, password, name, email, role FROM member";

        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public boolean update(User user) {

        String sql = "UPDATE member SET user_id=?, name=?, email=? WHERE id=?";

        int rs = jdbcTemplate.update(sql,
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getId()
        );

        if (rs == FAIL) {
            log.error("USER TABLE UPDATE FAIL ");
            return false;
        }

        return true;
    }

    @Override
    public User findOne(String userId, String password) {

        String sql = "SELECT id, user_id, password, name, email, role FROM member WHERE user_id=? AND password=?";

        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId, password);
    }

}
