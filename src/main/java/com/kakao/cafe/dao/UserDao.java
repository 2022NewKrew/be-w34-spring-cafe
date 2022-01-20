package com.kakao.cafe.dao;

import com.kakao.cafe.dao.mapper.UserRowMapper;
import com.kakao.cafe.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public void save(UserVo userVo) {
        String userId = userVo.getUserId();
        String password = userVo.getPassword();
        String name = userVo.getName();
        String email = userVo.getEmail();
        jdbcTemplate.update("INSERT INTO qna_user VALUES (?,?,?,?)",userId,password,name,email);
    }

    public UserVo findByUserId(String userId) {
        List<UserVo> resultList = jdbcTemplate.query("SELECT userid, password, name, email FROM qna_user WHERE userId = ?",userRowMapper,userId);
        return resultList.stream()
                .findFirst()
                .orElse(null);
    }

    public void update(UserVo userVo) {
        String userId = userVo.getUserId();
        String password = userVo.getPassword();
        String name = userVo.getName();
        String email = userVo.getEmail();
        jdbcTemplate.update("UPDATE qna_user SET password = ?, name = ?, email = ? WHERE userId = ?",password,name,email,userId);
    }

    public List<UserVo> findAll() {
        return jdbcTemplate.query("SELECT userid, password, name, email FROM qna_user",userRowMapper);
    }
}
