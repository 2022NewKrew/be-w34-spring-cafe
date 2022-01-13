package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcTemplateUserRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        final String sql = "insert into `user` (`nickname`, `email`, `name`, `password`) values(?,?,?,?)";
        Object[] parameters = {
                user.getNickname(),
                user.getEmail(),
                user.getName(),
                user.getPassword(),
        };
        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public void update(User user){
        final String sql = "update `user` set `name`=? , `password`=?, `email`=? where `id`=?";
        Object[] parameters = {
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getId(),
        };
        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public Optional<User> findById(Long id) {
        List<User> result =  jdbcTemplate.query("select * from `user` where id = ?", userRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        List<User> result =  jdbcTemplate.query("select * from `user` where name = ?", userRowMapper(), nickname);
        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from `user`", userRowMapper());
    }

    private RowMapper<User> userRowMapper(){
        return (rs, rowNum) -> User.builder()
                .id(rs.getLong("id"))
                .nickname(rs.getString("nickname"))
                .name(rs.getString("name"))
                .password(rs.getString("password"))
                .email(rs.getString("email"))
                .build();
    }
}
