package com.kakao.cafe.dao;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public boolean insert(User user) {
        try {
            String queryString = String.format("insert into Users (ID, PASSWORD, EMAIL, NAME) " +
                    "values ('%s', '%s', '%s', '%s');", user.getId(), user.getPassword(), user.getEmail(), user.getName());
            jdbcTemplate.execute(queryString);
            return true;
        } catch (DuplicateKeyException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    public User findById(String id) {
        try {
            String queryString = String.format("select id, password, email, name from users where ID = '%s'", id);
            Map<String, Object> res = jdbcTemplate.queryForMap(queryString);
            return mapToUser(res);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public Users findAll() {
        String queryString = "select * from users;";
        List<Map<String, Object>> res = jdbcTemplate.queryForList(queryString);
        return new Users(res.stream().map(this::mapToUser).collect(Collectors.toList()));
    }

    public boolean update(User user) {
        if (findById(user.getId()) == null)
            return false;
        String queryString = String.format("update users " +
                "set name = '%s', " +
                "email = '%s', " +
                "password = '%s' " +
                "where id = '%s';", user.getName(), user.getEmail(), user.getPassword(), user.getId());
        jdbcTemplate.execute(queryString);
        return true;
    }

    public void deleteAll() {
        String queryString = "delete from users";
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
