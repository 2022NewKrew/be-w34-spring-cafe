package com.kakao.cafe.repository;

import com.kakao.cafe.domain.reply.Reply;
import com.kakao.cafe.util.JdbcUtils;
import com.kakao.cafe.util.UtilClass;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Slf4j
public class JdbcReplyRepository implements RepositoryInterface<Reply> {
    private final DataSource dataSource;
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public JdbcReplyRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Reply save(Reply reply) {
        String sql = "insert into replies(content, date, writer, writerid, articleid) values(?, ?, ?, ?, ?)";
        reply.setDate(UtilClass.getLocalDateTimeNow());
        try {
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, reply.getContent());
            preparedStatement.setString(2, reply.getDate());
            preparedStatement.setString(3, reply.getWriter());
            preparedStatement.setLong(4, reply.getWriterId());
            preparedStatement.setLong(5, reply.getArticleId());

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public Optional<Reply> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Reply> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Reply> findAll() {
        return null;
    }

    @Override
    public Reply update(Reply reply) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
