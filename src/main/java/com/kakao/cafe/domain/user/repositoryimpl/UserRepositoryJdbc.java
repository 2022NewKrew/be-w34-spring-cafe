package com.kakao.cafe.domain.user.repositoryimpl;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserRepositoryJdbc")
public class UserRepositoryJdbc implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User save(User user) {
        if (user.isNew()) {
            insert(user);
            return user;
        }
        update(user);
        return user;
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT ID, STRING_ID, NAME, PASSWORD, EMAIL FROM `USER` WHERE ID = ?";
        try {
            return this.jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> User.builder()
                            .id(rs.getInt("ID"))
                            .stringId(rs.getString("STRING_ID"))
                            .name(rs.getString("NAME"))
                            .password(rs.getString("PASSWORD"))
                            .email(rs.getString("EMAIL"))
                            .build(),
                    id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User findByStringId(String stringId) {
        String sql = "SELECT ID, STRING_ID, NAME, PASSWORD, EMAIL FROM `USER` WHERE STRING_ID = ?";
        try {
            return this.jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> User.builder()
                            .id(rs.getInt("ID"))
                            .stringId(rs.getString("STRING_ID"))
                            .name(rs.getString("NAME"))
                            .password(rs.getString("PASSWORD"))
                            .email(rs.getString("EMAIL"))
                            .build(),
                    stringId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT ID, STRING_ID, NAME, PASSWORD, EMAIL FROM `USER`";
        return this.jdbcTemplate.query(sql,
                (rs, rowNum) -> User.builder()
                        .id(rs.getInt("ID"))
                        .stringId(rs.getString("STRING_ID"))
                        .name(rs.getString("NAME"))
                        .password(rs.getString("PASSWORD"))
                        .email(rs.getString("EMAIL"))
                        .build()
        );
    }

    private void insert(User user){
        String sql = "INSERT INTO `USER`(STRING_ID, NAME, PASSWORD, EMAIL) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                user.getStringId(), user.getName(), user.getPassword(), user.getEmail());
    }

    private void update(User user){
        String sql = "UPDATE `USER` SET STRING_ID=?, NAME=?, PASSWORD=?, EMAIL=? WHERE ID=?";
        jdbcTemplate.update(sql,
                user.getStringId(), user.getName(), user.getPassword(), user.getEmail(), user.getId());
    }
}
