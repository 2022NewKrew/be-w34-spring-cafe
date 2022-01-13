package com.kakao.cafe.impl.repository;

import com.kakao.cafe.dto.LoginDTO;
import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository("userRepository")
@Transactional
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long insertUser(UserDTO user) {
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
    public UserDTO getUserById(long id) {
        try {
            return jdbcTemplate
                    .queryForObject("select id, userid, password, email, to_char(time,'yyyy-MM-dd hh:mi') time from USERTABLE where id = ?",
                            (rs, rowNum) -> new UserDTO(
                                    rs.getLong("id"),
                                    rs.getString("userid"),
                                    rs.getString("password"),
                                    rs.getString("email"),
                                    rs.getString("time")
                            ), id);
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }

    @Override
    public List<UserDTO> getAllUser() {
        return jdbcTemplate.query("select id, userid, password, email, to_char(time,'yyyy-MM-dd hh:mi') time from USERTABLE",
                (rs, rowNum) -> new UserDTO(
                        rs.getLong("id"),
                        rs.getString("userid"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("time")
                )
        );
    }

    @Override
    public int updateUser(UserDTO user) {
        return jdbcTemplate.update("update USERTABLE set email = ?,password = ? where id = ?",
                user.getEmail(),
                user.getPassword(),
                user.getId());

    }

    @Override
    public UserDTO getUserByLoginData(LoginDTO login) {
        try {
            return jdbcTemplate
                    .queryForObject("select id, userid, password, email, to_char(time,'yyyy-MM-dd hh:mi') time from USERTABLE where email = ? and password = ?",
                            (rs, rowNum) -> new UserDTO(
                                    rs.getLong("id"),
                                    rs.getString("userid"),
                                    rs.getString("password"),
                                    rs.getString("email"),
                                    rs.getString("time")
                            ), login.getEmail(), login.getPassword());
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }
}
