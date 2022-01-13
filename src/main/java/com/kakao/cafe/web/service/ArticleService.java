package com.kakao.cafe.web.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleBoard;
import com.kakao.cafe.domain.ArticlePage;
import com.kakao.cafe.exception.NoArticleException;
import com.kakao.cafe.web.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

  private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);
  private final ArticleRepository articleRepository;

  public ArticleService(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  public ArticleBoard getArticleBoard(int pageIndex) {

    int totalSize = articleRepository.totalSize();
    ArticleBoard articleBoard = ArticleBoard.createEmpty(totalSize, pageIndex);
    ArticlePage articlePage = articleRepository.findByOffset(
        articleBoard.offset(), articleBoard.getArticlesPerPage()
    );
    articleBoard.setArticlePage(articlePage);

    return articleBoard;
  }

  public Article getArticle(int id) {
    return articleRepository.findById(id)
        .orElseThrow(NoArticleException::new);
  }

}
