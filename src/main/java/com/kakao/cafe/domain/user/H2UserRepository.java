package com.kakao.cafe.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class H2UserRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public H2UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        String userId = user.getUserId();
        String password = user.getPassword();
        String name = user.getName();
        String email = user.getEmail();

        jdbcTemplate.update(
                "INSERT INTO USERS(USERID, PASSWORD, NAME, EMAIL) VALUES(?, ?, ?, ?)", userId, password, name, email
        );
    }

    @Override
    public void update(String id, String password, User user) {

        jdbcTemplate.update(
                "UPDATE USERS SET NAME = ?, EMAIL = ? WHERE USERID = ? AND PASSWORD = ?" , user.getName(), user.getEmail(), id, password
        );
    }

    @Override
    public User findById(String id) {
        return DataAccessUtils.singleResult(jdbcTemplate.query(
                "SELECT USERID, PASSWORD, NAME, EMAIL FROM USERS WHERE USERID = ?",
                (rs, rowNum) -> {
                    User user = new User();
                    user.setUserId(rs.getString("USERID"));
                    user.setPassword(rs.getString("PASSWORD"));
                    user.setName(rs.getString("NAME"));
                    user.setEmail(rs.getString("EMAIL"));

                    return user;
                }
                , id
        ));
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(
                "SELECT USERID, PASSWORD, NAME, EMAIL FROM USERS",
                (rs, rowNum) -> {
                    User user = new User();
                    user.setUserId(rs.getString("USERID"));
                    user.setPassword(rs.getString("PASSWORD"));
                    user.setName(rs.getString("NAME"));
                    user.setEmail(rs.getString("EMAIL"));

                    return user;
                }
        );
    }
}
