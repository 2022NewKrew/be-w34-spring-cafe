package com.kakao.cafe.repository;

import com.kakao.cafe.dao.ArticleDao;
import com.kakao.cafe.dao.CommentDao;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Articles;
import com.kakao.cafe.domain.Id;
import com.kakao.cafe.domain.UserId;
import com.kakao.cafe.dto.ArticleDbDto;
import com.kakao.cafe.dto.ArticleSaveDto;
import com.kakao.cafe.dto.ArticleUpdateDto;
import com.kakao.cafe.dto.CommentDbDto;
import com.kakao.cafe.util.mapper.ArticleDbMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleJdbcRepository implements ArticleRepository {

    private final ArticleDao articleDao;
    private final CommentDao commentDao;

    public ArticleJdbcRepository(ArticleDao articleDao, CommentDao commentDao) {
        this.articleDao = articleDao;
        this.commentDao = commentDao;
    }

    @Override
    public boolean save(Article article, UserId userId) {
        ArticleSaveDto articleSaveDto = ArticleDbMapper.toArticleSaveDto(article, userId);
        int count = articleDao.insert(articleSaveDto);
        return count == 1;
    }

    @Override
    public Article findByArticleId(Id articleId) {
        ArticleDbDto articleDbDto = articleDao.findByArticleId(articleId.getId());
        List<CommentDbDto> commentDbDtos = commentDao.findByArticleId(articleId.getId());
        Article article = ArticleDbMapper.toArticle(articleDbDto, commentDbDtos);
        return article;
    }

    public Article findByArticleIdAndLoginId(Id articleId, UserId userId) {
        ArticleDbDto articleDbDto = articleDao.findByArticleIdAndLoginId(articleId.getId(), userId.getUserId());
        Article article = ArticleDbMapper.toArticle(articleDbDto);
        return article;
    }

    @Override
    public Articles findAll() {
        List<ArticleDbDto> articleDbDtos = articleDao.findAll();
        Articles articles = ArticleDbMapper.toArticles(articleDbDtos);
        return articles;
    }

    @Override
    public boolean update(Article article, UserId userId) {
        ArticleUpdateDto articleUpdateDto = ArticleDbMapper.toArticleUpdateDto(article, userId);
        int count = articleDao.update(articleUpdateDto);
        return count == 1;
    }

    @Override
    public boolean delete(Id articleId, UserId loginId) {
        // TODO: 게시글 작성자와 댓글 작성자가 다를 경우 삭제 불가능
        // 데이터 삭제 상태로 업데이트
        // 게시글을 삭제할 때 댓글도 삭제 한다.
        int count = articleDao.delete(articleId.getId(), loginId.getUserId());
        //commentDao.delete(articleId.getId());
        return count == 1;
    }
}
