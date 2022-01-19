package com.kakao.cafe.module.repository;

import com.kakao.cafe.module.model.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addReply(Reply reply) {
        String query = "INSERT INTO REPLY (article_id, author_id, comment) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, new String[]{"id"});
            preparedStatement.setLong(1, reply.getArticleId());
            preparedStatement.setLong(2, reply.getAuthorId());
            preparedStatement.setString(3, reply.getComment());
            return preparedStatement;
        };
        System.out.println(reply.getArticleId());
        System.out.println(reply.getAuthorId());
        System.out.println(reply.getComment());
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
    }
}
