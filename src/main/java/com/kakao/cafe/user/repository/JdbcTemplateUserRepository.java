package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.SignUpDTO;
import com.kakao.cafe.user.dto.UpdateDTO;
import com.kakao.cafe.user.factory.UserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Primary
@Transactional
@Repository
@RequiredArgsConstructor
public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(SignUpDTO singUpDto) {
        User user = UserFactory.toUser(singUpDto);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement("insert into USERS (USER_ID,PASSWORD,NAME,EMAIL)" +
                            "values (?,?,?,?)",
                    new String[]{"ID"});
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            return pstmt;
        }, keyHolder);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUserId(String userId) {
        return jdbcTemplate.query("select * from USERS where USER_ID = ?", userRowMapper(), userId)
                .stream().findAny();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return jdbcTemplate.query("select * from USERS", userRowMapper());
    }

    @Override
    public void update(UpdateDTO updateDto) {
        jdbcTemplate.update("update USERS set PASSWORD=?,NAME=?,EMAIL=? where ID=?"
                , updateDto.getNewPassword(), updateDto.getName(), updateDto.getEmail(),
                updateDto.getId());
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(
                rs.getLong("ID"),
                rs.getString("USER_ID"),
                rs.getString("PASSWORD"),
                rs.getString("NAME"),
                rs.getString("EMAIL"));
    }
}
