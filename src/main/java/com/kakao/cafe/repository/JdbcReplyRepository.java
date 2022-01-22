package com.kakao.cafe.repository;

import com.kakao.cafe.domain.reply.Reply;
import com.kakao.cafe.util.JdbcUtils;
import com.kakao.cafe.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class JdbcReplyRepository implements ReplyInterface {
    private static final String ALL_OF_REPLY = "select id, content, date, u.name as writer, " +
            "r.writerid as writerid, articleid, deleted from replies as r join users as u " +
            "where r.writerid = u.userid AND deleted=false";
    private static final String ORDERED = " order by id desc";
    private final DataSource dataSource;
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public Reply save(Reply reply) {
        String sql = "insert into replies(content, date, writerid, articleid, deleted) values(?, ?, ?, ?, ?)";
        reply.setDate(DateUtils.getLocalDateTimeNow());
        try {
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, reply.getContent());
            preparedStatement.setString(2, reply.getDate());
            preparedStatement.setLong(3, reply.getWriterId());
            preparedStatement.setLong(4, reply.getArticleId());
            preparedStatement.setBoolean(5, reply.getDeleted());


            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                reply.setId(resultSet.getLong(1));
                log.info("reply " + reply + " saved");
                return reply;
            }
            throw new SQLException("Reply 생성 실패");
        } catch (Exception e) {
            log.info(reply.toString());
            throw new IllegalStateException(e);
        } finally {
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Optional<Reply> findById(Long id) {
        String sql = ALL_OF_REPLY + " AND id=?";

        try {
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(getResult(resultSet));
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Optional<Reply> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Reply> findAll() {
        String sql = ALL_OF_REPLY + ORDERED;

        try {
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            List<Reply> replies = new ArrayList<>();
            while (resultSet.next()) {
                replies.add(getResult(resultSet));
            }
            return replies;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Reply update(Reply reply) {
        return null;
    }

    @Override
    public void delete(Long id) {
        String sql = "update replies set deleted=true where id = ?";
        try {
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Reply> findByArticleId(Long articleId) {
        String sql = ALL_OF_REPLY + " AND articleid = ?" + ORDERED;

        try {
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, articleId);

            resultSet = preparedStatement.executeQuery();

            List<Reply> replies = new ArrayList<>();
            while (resultSet.next()) {
                replies.add(getResult(resultSet));
            }
            return replies;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void deleteAllReplyOnArticle(Long articleId) {
        String sql = "update replies set deleted=true where articleId=?";
        try {
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, articleId);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }

    }

    private Reply getResult(ResultSet resultSet) {
        try {
            Reply reply = new Reply();
            reply.setId(resultSet.getLong("id"));
            reply.setContent(resultSet.getString("content"));
            reply.setDate(resultSet.getString("date"));
            reply.setWriter(resultSet.getString("writer"));
            reply.setWriterId(resultSet.getLong("writerid"));
            reply.setArticleId(resultSet.getLong("articleid"));
            reply.setDeleted(resultSet.getBoolean("deleted"));
            return reply;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
