package com.kakao.cafe.dao;

import com.kakao.cafe.dao.mapper.UserRowMapper;
import com.kakao.cafe.vo.UserVo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public UserDao(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    public void save(UserVo userVo) {
        String userId = userVo.getUserId();
        String password = userVo.getPassword();
        String name = userVo.getName();
        String email = userVo.getEmail();
        jdbcTemplate.update("INSERT INTO QNA_USER VALUES (?,?,?,?)",userId,password,name,email);
    }

    public UserVo findByUserId(String userId) {
        List<UserVo> resultList = jdbcTemplate.query("SELECT userid, password, name, email FROM QNA_USER WHERE userId = ?",userRowMapper,userId);
        return resultList.stream()
                .findFirst()
                .orElse(null);
    }

    public void update(UserVo userVo) {
        String userId = userVo.getUserId();
        String password = userVo.getPassword();
        String name = userVo.getName();
        String email = userVo.getEmail();
        jdbcTemplate.update("UPDATE QNA_USER SET password = ?, name = ?, email = ? WHERE userId = ?",password,name,email,userId);
    }

    public List<UserVo> findAll() {
        return jdbcTemplate.query("SELECT userid, password, name, email FROM QNA_USER",userRowMapper);
    }
}
