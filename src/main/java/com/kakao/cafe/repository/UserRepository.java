package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public void insert(User user) {
        String queryString = String.format("insert into Users (ID, PASSWORD, EMAIL, NAME) " +
                "values ('%s', '%s', '%s', '%s');", user.getId(), user.getPassword(), user.getEmail(), user.getName());
        jdbcTemplate.execute(queryString);
    }

    public User findById(String id) {
        String queryString = String.format("select id, password, email, name from users where ID = '%s'", id);
        Map<String, Object> res = jdbcTemplate.queryForMap(queryString);
        return new User.Builder()
                .email((String) res.get("EMAIL"))
                .id((String) res.get("ID"))
                .name((String) res.get("NAME"))
                .password((String) res.get("PASSWORD")).build();
    }

    public Users findAll() {
        String queryString = "select * from users;";
        List<Map<String, Object>> res = jdbcTemplate.queryForList(queryString);
        return new Users(res.stream().map(this::mapToUser).collect(Collectors.toList()));
    }

    public void update(User user) {
        String queryString = String.format("update users " +
                "set name = '%s', " +
                "email = '%s', " +
                "password = '%s' " +
                "where id = '%s';", user.getName(), user.getEmail(), user.getPassword(), user.getId());
        jdbcTemplate.execute(queryString);
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
