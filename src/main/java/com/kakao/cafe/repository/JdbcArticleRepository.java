package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.util.JdbcUtils;
import com.kakao.cafe.util.UtilClass;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class JdbcArticleRepository implements RepositoryInterface<Article>{
    private final DataSource dataSource;

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public JdbcArticleRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Article save(Article article) {
        String sql = "insert into articles(title, content, date, writer, view) values(?, ?, ?, ?, ?)";
        article.setDate(UtilClass.getLocalDateTimeNow());
        article.setView(0L);

        try{
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setString(3, article.getDate());
            preparedStatement.setString(4, article.getWriter());
            preparedStatement.setLong(5, article.getView());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                article.setIndex(resultSet.getLong(1));
                log.info("OK");
                return article;
            }
            throw new SQLException("Index 조회 실패");
        }catch(Exception e){
            log.info(article.toString());
            throw new IllegalStateException(e);
        }finally{
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Optional<Article> findById(Long index) {
        String sql = "select * from articles where index = ?";

        try{
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, index);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return Optional.of(getResult(resultSet));
            }
            return Optional.empty();
        }catch(Exception e){
            throw new IllegalStateException(e);
        }finally{
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Optional<Article> findByName(String title) {
        String sql = "select * from articles where title = ?";

        try{
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return Optional.of(getResult(resultSet));
            }
            return Optional.empty();
        }catch(Exception e){
            throw new IllegalStateException(e);
        }finally{
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Article> findAll() {
        String sql = "select * from articles";
        try{
            connection = JdbcUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            List<Article> articles = new ArrayList<>();
            while(resultSet.next()){
                articles.add(getResult(resultSet));
            }
            return articles;
        }catch(Exception e){
            throw new IllegalStateException(e);
        }finally{
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Article update(Article article) {
        return null;
    }

    private Article getResult(ResultSet resultSet){
        try {
            Article article = new Article();
            article.setIndex(resultSet.getLong("index"));
            article.setTitle(resultSet.getString("title"));
            article.setContent(resultSet.getString("content"));
            article.setDate(resultSet.getString("date"));
            article.setWriter(resultSet.getString("writer"));
            article.setView(resultSet.getLong("view"));
            return article;
        }catch(Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
