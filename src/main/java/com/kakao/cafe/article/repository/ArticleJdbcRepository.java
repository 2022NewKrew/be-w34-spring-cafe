package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleRowMapper;
import com.kakao.cafe.article.domain.Reply;
import com.kakao.cafe.article.domain.ReplyViewRowMapper;
import com.kakao.cafe.article.dto.ReplyViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleJdbcRepository implements ArticleRepository{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleJdbcRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addArticle(Article article) {
        String sql = "INSERT INTO articles(userId,name,title,contents,createdAt) VALUES (?,?,?,?,CURRENT_TIMESTAMP())";
        jdbcTemplate.update(sql,
                article.getUserId(),
                article.getName(),
                article.getTitle(),
                article.getContents()
        );
    }

    @Override
    public void updateArticle(Long sequence, String title, String contents) {
        String sql = String.format("UPDATE articles SET title='%s', contents='%s' WHERE sequence=%d", title, contents, sequence);
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteArticle(Article article) {
        String sql = String.format("UPDATE articles SET isDeleted=true WHERE sequence=%d", article.getSequence());
        jdbcTemplate.update(sql);
    }

    @Override
    public List<Article> getArticlesNotDeleted() {
        String sql = "SELECT * FROM articles WHERE isDeleted=false";
        return jdbcTemplate.query(sql, new ArticleRowMapper());
    }

    @Override
    public Article getArticleByCondition(String key, String value) {
        String sql = String.format("SELECT * FROM articles WHERE %s = %s", key, value);
        return (Article) jdbcTemplate.query(sql, new ArticleRowMapper()).stream().findAny().orElse(null);
    }

    @Override
    public void addReply(String userId, Long articleSeq, String contents) {
        String sql = "INSERT INTO REPLY(userId,articleSeq,contents,createdAt) VALUES (?,?,?,CURRENT_TIMESTAMP())";
        jdbcTemplate.update(sql,
                userId,
                articleSeq,
                contents);
    }

    //이 부분을 join없이 List<Reply> 로만 얻고싶은데 다른방법이 없을까 고민중이다.
    @Override
    public List<ReplyViewDTO> getRepliesByArticleSeqWithUser(Long articleSeq) {
        String sql = String.format("SELECT * FROM REPLY JOIN USERS ON REPLY.USERID = USERS.USERID WHERE REPLY.ARTICLESEQ=%d;", articleSeq);
        return jdbcTemplate.query(sql, new ReplyViewRowMapper());
    }
}
