package com.kakao.cafe.adapter.out.infra.persistence.user;

import com.kakao.cafe.domain.user.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class JdbcUserInfoRepository implements UserInfoRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserInfoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("user");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", user.getUserId());
        parameters.put("password", user.getPassword());
        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());

        simpleJdbcInsert.execute(parameters);
    }

    @Override
    public void update(User user) {
        String sql = "update user set name=?, email=? where id=?";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getUserId());
    }

    @Override
    public List<UserVO> getAllUserList() {
        String sql = "select * from user";
        return jdbcTemplate.query(
            sql,
            (rs, count) -> new UserVO(
                rs.getString("id"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email")
            )
        );
    }

    @Override
    public Optional<UserVO> findByUserId(String userId) {
        String sql = "select * from user where id = ?";
        List<UserVO> userVOList = jdbcTemplate.query(
            sql,
            (rs, count) -> new UserVO(
                rs.getString("id"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email")
            ), userId
        );
        return userVOList.size() == 0 ? Optional.empty()
                                      : Optional.of(userVOList.get(0));  // TODO : size >= 2 일 때 예외처리 필요
    }
}
