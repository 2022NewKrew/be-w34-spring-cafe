package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleDTO;
import com.kakao.cafe.repository.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static com.kakao.cafe.repository.article.ArticleRepositoryQueryAndNameSpace.ColumnName.*;

public class ArticleRepository implements Repository<Article, ArticleDTO, Integer> {
    private final JdbcTemplate jdbcTemplate;
    private final ArticleRepositoryQueryAndNameSpace queryAndNameSpace;

    public ArticleRepository(DataSource dataSource, ArticleRepositoryQueryAndNameSpace queryAndNameSpace) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.queryAndNameSpace = queryAndNameSpace;
    }

    @Override
    public Article save(ArticleDTO articleDTO) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName(queryAndNameSpace.getTableName()).usingGeneratedKeyColumns(ID.getColumnName());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(WRITER.getColumnName(), articleDTO.getWriter());
        parameters.put(TITLE.getColumnName(), articleDTO.getTitle());
        parameters.put(CONTENTS.getColumnName(), articleDTO.getContents());
        parameters.put(DATE.getColumnName(), articleDTO.getDate());
        parameters.put(COMMENT_SIZE.getColumnName(), articleDTO.getCommentSize());
        parameters.put(PARENT.getColumnName(), articleDTO.getParent());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        articleDTO.setId(key.intValue());

        return new Article(articleDTO);
    }

    @Override
    public Optional<Article> findById(Integer id) {
        List<Article> result = jdbcTemplate.query(queryAndNameSpace.getFindByIdSqlQuery(), articleRowMapper(), id);

        Optional<Article> resultArticle = result.stream().findAny();
        resultArticle.ifPresent(article -> article.getComments().addAll(findComments(id)));

        return resultArticle;
    }

    public List<Article> findComments(Integer id){
        return jdbcTemplate.query(queryAndNameSpace.getFindCommentsSqlQuery(), articleRowMapper(), id);
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(queryAndNameSpace.getFindAllSqlQuery(), articleRowMapper());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(queryAndNameSpace.getDeleteSqlQuery(), id);
    }

    private RowMapper<Article> articleRowMapper(){
        return (rs, rowNum) -> {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setId(rs.getInt(ID.getColumnName()));
            articleDTO.setWriter(rs.getString(WRITER.getColumnName()));
            articleDTO.setTitle(rs.getString(TITLE.getColumnName()));
            articleDTO.setContents(rs.getString(CONTENTS.getColumnName()));
            articleDTO.setDate(rs.getObject(DATE.getColumnName(), LocalDateTime.class));
            articleDTO.setCommentSize(rs.getInt(COMMENT_SIZE.getColumnName()));
            articleDTO.setParent(rs.getInt(PARENT.getColumnName()));
            articleDTO.setComments(new ArrayList<>());

            return new Article(articleDTO);
        };
    }
}
