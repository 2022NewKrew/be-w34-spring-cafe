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
import com.kakao.cafe.exception.NotFoundException;
import com.kakao.cafe.util.ErrorCode;
import com.kakao.cafe.util.mapper.ArticleDbMapper;
import org.springframework.dao.EmptyResultDataAccessException;
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
        try {
            ArticleDbDto articleDbDto = articleDao.findByArticleId(articleId.getId());
            List<CommentDbDto> commentDbDtos = commentDao.findByArticleId(articleId.getId());
            return ArticleDbMapper.toArticle(articleDbDto, commentDbDtos);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(ErrorCode.ARTICLE_NOT_FOUND);
        }
    }

    public Article findByArticleIdAndLoginId(Id articleId, UserId userId) {
        ArticleDbDto articleDbDto = articleDao.findByArticleIdAndLoginId(articleId.getId(), userId.getUserId());
        return ArticleDbMapper.toArticle(articleDbDto);
    }

    @Override
    public Articles findAll() {
        List<ArticleDbDto> articleDbDtos = articleDao.findAll();
        return ArticleDbMapper.toArticles(articleDbDtos);
    }

    @Override
    public boolean update(Article article, UserId userId) {
        ArticleUpdateDto articleUpdateDto = ArticleDbMapper.toArticleUpdateDto(article, userId);
        int count = articleDao.update(articleUpdateDto);
        return count == 1;
    }

    @Override
    public boolean delete(Id articleId, UserId loginId) {
        boolean isSameUser = commentDao.countByArticleIdAndUserId(articleId.getId(), loginId.getUserId()) == 0;
        if (isSameUser) {
            int count = articleDao.delete(articleId.getId(), loginId.getUserId());
            commentDao.deleteByArticleIdAndUserId(articleId.getId(), loginId.getUserId());
            return count == 1;
        }
        return false;
    }
}
