package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.dao.ArticleFindDao;
import com.kakao.cafe.domain.article.dto.ArticleResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleListService {

    private final ArticleFindDao articleFindDao;

    public ArticleListService(ArticleFindDao articleFindDao) {
        this.articleFindDao = articleFindDao;
    }

    public List<ArticleResponseDto> showArticleList() {
        List<Article> articles = articleFindDao.findAll();
        return ArticleResponseDto.of(articles);
    }
}
