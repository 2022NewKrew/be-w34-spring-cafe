package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public void save(User user) {
        String query = "INSERT INTO Users (userId, userPassword, userName, email) "
        + String.format("VALUES ('%s', '%s', '%s', '%s')", user.getUserId(), user.getPassWord(), user.getUserName(), user.getEmail());

        jdbcTemplate.execute(query);
    }

    public List<User> findAll() {
        String query = "SELECT * FROM Users";
        List<User> users = new ArrayList<>();
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

        for (Map<String, Object> userInfo: result) {
            users.add(
                    new User((String) userInfo.get("userId"),
                             (String) userInfo.get("userPassword"),
                             (String) userInfo.get("userName"),
                             (String) userInfo.get("email"))
            );
        }

        return users;
    }

    public User findById(String userId) {
        String query = String.format("SELECT userId, userPassword, userName, email FROM Users WHERE userId = '%s'", userId);
        Map<String, Object> result = jdbcTemplate.queryForMap(query);
        return new User((String) result.get("userId"),
                        (String) result.get("userPassword"),
                        (String) result.get("userName"),
                        (String) result.get("email"));
    }
}
