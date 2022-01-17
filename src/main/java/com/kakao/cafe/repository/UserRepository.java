package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.user.ProfileDto;
import com.kakao.cafe.dto.user.SimpleUserInfo;
import com.kakao.cafe.rowmapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final UserRowMapper userRowMapper;

    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM `USER` WHERE id = ?";
        User user = jdbcTemplate.queryForObject(sql, userRowMapper.getUserRowMapper(), id);
        return Optional.ofNullable(user);
    }

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM `USER` WHERE email = ?";
        User user = jdbcTemplate.queryForObject(sql, userRowMapper.getUserRowMapper(), email);
        return Optional.ofNullable(user);
    }

    public Optional<SimpleUserInfo> findSimpleUserInfoById(Long userId) {
        String sql = "SELECT email, nick_name, created_at FROM `USER` WHERE id = ?";
        SimpleUserInfo simpleUserInfo = jdbcTemplate.queryForObject(sql, userRowMapper.getSimpleUserInfoMapper(), userId);
        return Optional.ofNullable(simpleUserInfo);
    }

    public Optional<ProfileDto> findProfileById(Long userId) {
        String sql = "SELECT id, nick_name, password FROM `USER` WHERE id = ?";
        ProfileDto profileDto = jdbcTemplate.queryForObject(sql, userRowMapper.getProfileDtoMapper(), userId);
        return Optional.ofNullable(profileDto);
    }

    public List<SimpleUserInfo> getListOfSimpleUserInfo(Integer pageNum, Integer pageSize) {
        int offSet = (pageNum - 1) * pageSize;
        int limit = pageSize;
        String sql = "SELECT email, nick_name, created_at FROM `USER` LIMIT " + limit + " OFFSET " + offSet;
        return jdbcTemplate.query(sql, userRowMapper.getSimpleUserInfoMapper());
    }

    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(email) FROM `USER` WHERE email = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count > 0;
    }

    public boolean existsByNickName(String nickName) {
        String sql = "SELECT COUNT(nick_name) FROM `USER` WHERE nick_name = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, nickName);
        return count > 0;
    }

    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(id) FROM `USER` WHERE id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count > 0;
    }

    public User save(User user) {
        if(user.getId() == null) {
            return add(user);
        }
        return update(user);
    }

    private User add(User user) {
        String sql = "INSERT INTO `USER` (email, password, nick_name, created_at) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps =
                    con.prepareStatement(sql, new String[] {"id"});
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNickName());
            ps.setTimestamp(4, Timestamp.valueOf(user.getCreatedAt()));
            return ps;
        },  keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    private User update(User user) {
        String sql = "UPDATE `USER` SET password = ?, nick_name = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getPassword(), user.getNickName(), user.getId());
        return user;
    }

    public int countAll() {
        String sql = "SELECT COUNT(id) FROM `USER`";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
