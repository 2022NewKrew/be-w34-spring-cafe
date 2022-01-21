package com.kakao.cafe.impl.repository;

import com.kakao.cafe.dto.LoginDTO;
import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public long insertUser(UserDTO user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();


        try {

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement("insert into User (userid, password, email) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getUserId());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                return ps;
            }, keyHolder);
        } catch (DuplicateKeyException exception) {
            return -1;
        }
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public UserDTO getUserById(long id) {
        try {
            return jdbcTemplate
                    .queryForObject("select id, userid, password, email, date_format(time,'%Y-%m-%d %H:%i') time from User where id = ?",
                            getRowMapper(), id);
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }

    @Override
    public List<UserDTO> getAllUser() {
        return jdbcTemplate.query("select id, userid, password, email, date_format(time,'%Y-%m-%d %H:%i') time from User",
                getRowMapper()
        );
    }

    @Override
    public int updateUser(UserDTO user) {
        return jdbcTemplate.update("update User set email = ?,password = ? where id = ?",
                user.getEmail(),
                user.getPassword(),
                user.getId());

    }

    @Override
    public UserDTO getUserByLoginData(LoginDTO login) {
        try {
            return jdbcTemplate.queryForObject("select id, userid, password, email, date_format(time,'%Y-%m-%d %H:%i') time from User where email = ? and password = ?",
                    getRowMapper(), login.getEmail(), login.getPassword());
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }

    RowMapper<UserDTO> getRowMapper() {
        return (rs, rowNum) -> new UserDTO(
                rs.getLong("id"),
                rs.getString("userid"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getString("time")
        );
    }
}
