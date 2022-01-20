package com.kakao.cafe.global.mapper;

import com.kakao.cafe.article.persistence.Article;
import com.kakao.cafe.article.persistence.Reply;
import com.kakao.cafe.article.repository.ReplyRepository;
import com.kakao.cafe.user.exception.UserNotFoundException;
import com.kakao.cafe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticleRowMapper implements RowMapper<Article> {

    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("id");
        long writerId = rs.getLong("writer_id");
        String title = rs.getString("title");
        String content = rs.getString("content");
        List<Reply> replies = replyRepository.findByArticleId(id);
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();

        return new Article(
                id,
                userRepository.findById(writerId).orElseThrow(UserNotFoundException::new),
                title,
                content,
                replies,
                createdAt,
                updatedAt
        );
    }
}
