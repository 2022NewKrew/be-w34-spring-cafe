package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.util.DateUtils;
import com.kakao.cafe.util.JdbcUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class JdbcArticleRepository implements RepositoryInterface<Article> {
    private static final String ALL_OF_ARTICLE = "select `index`, title, content, date, u.name as writer," +
            "a.writerid as writerid, view, deleted from articles as a join users as u " +
            "where a.writerid = u.userid AND deleted=false";
    private static final String ORDERED = " order by `index` desc";
    private final DataSource dataSource;
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public Article save(Article article) {
        String sql = "insert into articles(title, content, date, writerid, view, deleted) values(?, ?, ?, ?, ?, ?)";
        article.setDate(DateUtils.getLocalDateTimeNow());
        article.setView(0L);

        try {
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setString(3, article.getDate());
            preparedStatement.setLong(4, article.getWriterId());
            preparedStatement.setLong(5, article.getView());
            preparedStatement.setBoolean(6, false);

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                article.setIndex(resultSet.getLong(1));
                log.info("article " + article.getIndex() + " saved");
                return article;
            }
            throw new SQLException("Article 생성 실패");
        } catch (Exception e) {
            log.info(article.toString());
            throw new IllegalStateException(e);
        } finally {
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Optional<Article> findById(Long index) {
        String sql = ALL_OF_ARTICLE + " AND `index` = ?";

        try {
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, index);

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
    public Optional<Article> findByName(String title) {
        String sql = ALL_OF_ARTICLE + " AND title = ?" + ORDERED;

        try {
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);

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
    public List<Article> findAll() {
        String sql = ALL_OF_ARTICLE + ORDERED;
        try {
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            List<Article> articles = new ArrayList<>();
            while (resultSet.next()) {
                articles.add(getResult(resultSet));
            }
            return articles;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Article update(Article article) {
        String sql = "update articles set title = ?, content = ?, date = ? where `index` = ?";
        article.setDate(DateUtils.getLocalDateTimeNow());
        try {
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setString(3, article.getDate());
            preparedStatement.setLong(4, article.getIndex());

            preparedStatement.executeUpdate();
            return article;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "update articles set deleted=true where `index` = ?";
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

    private Article getResult(ResultSet resultSet) {
        try {
            Article article = new Article();
            article.setIndex(resultSet.getLong("index"));
            article.setTitle(resultSet.getString("title"));
            article.setContent(resultSet.getString("content"));
            article.setDate(resultSet.getString("date"));
            article.setWriter(resultSet.getString("writer"));
            article.setWriterId(resultSet.getLong("writerid"));
            article.setView(resultSet.getLong("view"));
            article.setDeleted(resultSet.getBoolean("deleted"));
            return article;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
