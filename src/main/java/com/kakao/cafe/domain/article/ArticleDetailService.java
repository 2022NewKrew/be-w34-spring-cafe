package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.dao.ArticleFindDao;
import com.kakao.cafe.domain.article.dto.ArticleResponseDto;
import org.springframework.stereotype.Service;

@Service
public class ArticleDetailService {

    private final ArticleFindDao articleFindDao;

    public ArticleDetailService(ArticleFindDao articleFindDao) {
        this.articleFindDao = articleFindDao;
    }

    public ArticleResponseDto showArticleDetail(Long id) {
        Article article = articleFindDao.findByid(id);
        return ArticleResponseDto.of(article);
    }
}
