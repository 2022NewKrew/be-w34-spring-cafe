package com.kakao.cafe.dao;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void insert(User user) {
        String queryString = "insert into USERS (ID, PASSWORD, EMAIL, NAME) values (?, ?, ?, ?);";
        jdbcTemplate.update(queryString, user.getId(), user.getPassword(), user.getEmail(), user.getName());
    }

    public User findById(String id) {
        String queryString = "select id, password, email, name from USERS where ID = ?";
        Map<String, Object> res = jdbcTemplate.queryForMap(queryString, id);
        return mapToUser(res);
    }

    public Users findAll() {
        String queryString = "select * from USERS;";
        List<Map<String, Object>> res = jdbcTemplate.queryForList(queryString);
        return new Users(res.stream().map(this::mapToUser).collect(Collectors.toList()));
    }

    public int update(User user) {
        String queryString = "update USERS set name = ?, email = ?, password = ? where id = ?;";
        return jdbcTemplate.update(queryString, user.getName(), user.getEmail(), user.getPassword(), user.getId());
    }

    public void deleteAll() {
        String queryString = "delete from USERS";
        jdbcTemplate.execute(queryString);
    }

    private User mapToUser(Map<String, Object> res) {
        return new User.Builder()
                .email((String) res.get("EMAIL"))
                .id((String) res.get("ID"))
                .name((String) res.get("NAME"))
                .password((String) res.get("PASSWORD")).build();
    }

}
