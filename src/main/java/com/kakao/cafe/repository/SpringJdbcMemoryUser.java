package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.SampleArticleForm;
import com.kakao.cafe.dto.SampleUserForm;
import com.kakao.cafe.util.CustomException;
import com.kakao.cafe.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.kakao.cafe.util.ErrorCode.NOT_EXIST_USER;

public class SpringJdbcMemoryUser implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SpringJdbcMemoryUser(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User save(SampleUserForm form) {

        User user = User.add(form);
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("members").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("uid", user.getUid());
        parameters.put("password", user.getPassWord());
        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());
        parameters.put("content", user.getContent());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        user.setId(key.longValue());

        return user;
    }

    @Override
    public void update(User user){
        jdbcTemplate.update("update members set uid=?, name=?, email=?, content=? where id=?",user.getUid(), user.getName(), user.getEmail(), user.getContent(), user.getId());
    }

    @Override
    public User findByUserID(String uID) {
        List<User> result =  jdbcTemplate.query("select * from members where uid = ?", userRowMapper(), uID);
        return result.stream().findAny().orElseThrow(() -> new CustomException(NOT_EXIST_USER));
    }

    @Override
    public User findByNumID(Long ID) {
        List<User> result =  jdbcTemplate.query("select * from members where id = ?", userRowMapper(), ID);
        return result.stream().findAny().orElseThrow(() -> new CustomException(NOT_EXIST_USER));
    }

    @Override
    public Boolean checkExistenceByUserID(String uID) {
        List<User> result =  jdbcTemplate.query("select * from members where uid = ?", userRowMapper(), uID);
        return result.stream().findAny().isPresent();
    }


    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from members", userRowMapper());
    }

    private RowMapper<User> userRowMapper(){
        return new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                String uid = rs.getString("uid");
                String passWord = rs.getString("passWord");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String content = rs.getString("content");
                User user = User.add(new SampleUserForm(uid, passWord, name, email, content));
                user.setId(rs.getLong("id"));
                return user;
            }
        };
    }
}
