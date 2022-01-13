package com.kakao.cafe.web.controller.api;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.web.common.EnableSession;
import com.kakao.cafe.web.controller.KakaoCafeApiController;
import com.kakao.cafe.web.dto.ArticleDTO;
import com.kakao.cafe.web.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@KakaoCafeApiController
@EnableSession
public class ArticleApiController {

  private static final Logger logger = LoggerFactory.getLogger(ArticleApiController.class);
  private final ArticleService articleService;

  public ArticleApiController(ArticleService articleService) {
    this.articleService = articleService;
  }

  @PostMapping("/article")
  public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO) {

    Article createdArticle = articleService.createArticle(Article.of(articleDTO));

    return ResponseEntity.status(HttpStatus.FOUND)
        .header(HttpHeaders.LOCATION, "/article/" + createdArticle.getId())
        .body(new ArticleDTO(createdArticle));
  }

}
