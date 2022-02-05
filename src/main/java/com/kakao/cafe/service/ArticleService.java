package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Articles;
import com.kakao.cafe.domain.Id;
import com.kakao.cafe.domain.UserId;
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
        }
    }

    public Article findByArticleId(Id articleId) {
        return articleRepository.findByArticleId(articleId);
//        if (article.isEmpty()) {
//            throw new ArticleException(ErrorCode.ARTICLE_NOT_FOUND);
//        }
//        return article.get();
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
        // 로그인 사용자와 게시글 작성자가 같다
        // 게시글 작성자와 댓글 작성자가 모두 같은 경우 삭제가 가능하다
        boolean isDeleted = articleRepository.delete(articleId, loginId);
        if (!isDeleted) {
            throw new UserAccessException(ErrorCode.ACCESS_DENIED_USER);
        }
    }

    public Article findByArticleIdAndLoginId(Id articleId, UserId loginId) {
        // 로그인 사용자와 게시글 작성자가 같다
        return articleRepository.findByArticleIdAndLoginId(articleId, loginId);
    }
}
