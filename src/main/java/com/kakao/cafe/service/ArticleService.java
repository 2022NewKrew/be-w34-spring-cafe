package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Articles;
import com.kakao.cafe.domain.Id;
import com.kakao.cafe.domain.UserId;
import com.kakao.cafe.exception.SaveException;
import com.kakao.cafe.exception.UserAccessException;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.util.ErrorCode;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void post(Article article, UserId userId) {
        boolean isSaved = articleRepository.save(article, userId);
        if (!isSaved) {
            throw new SaveException(ErrorCode.CANNOT_SAVE_ARTICLE);
        }
    }

    public Article findByArticleId(Id articleId) {
        return articleRepository.findByArticleId(articleId);
    }

    public Articles findAll() {
        return articleRepository.findAll();
    }

    public void modify(Article article, UserId loginId) {
        boolean isUpdate = articleRepository.update(article, loginId);
        if (!isUpdate) {
            throw new UserAccessException(ErrorCode.ACCESS_DENIED_USER);
        }
    }

    public void delete(Id articleId, UserId loginId) {
        boolean isDeleted = articleRepository.delete(articleId, loginId);
        if (!isDeleted) {
            throw new UserAccessException(ErrorCode.ACCESS_DENIED_USER);
        }
    }

    public Article findByArticleIdAndLoginId(Id articleId, UserId loginId) {
        return articleRepository.findByArticleIdAndLoginId(articleId, loginId);
    }
}
