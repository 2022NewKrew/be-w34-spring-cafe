package com.kakao.cafe.global.mapper;

import com.kakao.cafe.article.persistence.Reply;
import com.kakao.cafe.user.exception.UserNotFoundException;
import com.kakao.cafe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ReplyRowMapper implements RowMapper<Reply> {

    private final UserRepository userRepository;

    @Override
    public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("id");
        long writerId = rs.getLong("writer_id");
        String content = rs.getString("content");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();

        return new Reply(
                id,
                userRepository.findById(writerId).orElseThrow(UserNotFoundException::new),
                content,
                createdAt
        );
    }
}
