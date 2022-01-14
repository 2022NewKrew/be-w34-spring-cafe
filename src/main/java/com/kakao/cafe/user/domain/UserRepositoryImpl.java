package com.kakao.cafe.user.domain;

import com.kakao.cafe.user.dto.Profile;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        final String sql = "insert into users(email, password, nickname) values(?, ?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getNickname());
    }

    @Override
    public Optional<Profile> findById(Long id) {
        final String sql = "select * from users where user_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> Profile.builder()
                    .userId(rs.getLong("user_id"))
                    .email(rs.getString("email"))
                    .nickname(rs.getString("nickname"))
                    .createdAt(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate())
                    .build(),
                id));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Profile> findAll() {
        final String sql = "select * from users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> Profile.builder()
            .userId(rs.getLong("user_id"))
            .email(rs.getString("email"))
            .nickname(rs.getString("nickname"))
            .createdAt(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate())
            .build());
    }
}
