package com.kakao.cafe.web.controller.mvc;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleBoard;
import com.kakao.cafe.web.common.EnableSession;
import com.kakao.cafe.web.common.RequireLogin;
import com.kakao.cafe.web.controller.KakaoCafePageController;
import com.kakao.cafe.web.dto.ArticleBoardDTO;
import com.kakao.cafe.web.dto.ArticleDTO;
import com.kakao.cafe.web.service.ArticleService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@KakaoCafePageController
@EnableSession
public class ArticlePageController {

  private static final Logger logger = LoggerFactory.getLogger(ArticlePageController.class);
  private final ArticleService articleService;

  public ArticlePageController(ArticleService articleService) {
    this.articleService = articleService;
  }


  @GetMapping({"/articles", "/articles/{pageIndex}"})
  public String getArticleBoard(Model model, @PathVariable Optional<Integer> pageIndex) {

    int boardPageIndex = pageIndex.orElse(ArticleBoard.FIRST_PAGE_INDEX);
    ArticleBoard articleBoard = articleService.getArticleBoard(boardPageIndex);
    model.addAttribute("articleBoard", new ArticleBoardDTO(articleBoard));

    return "articles";
  }


  @RequireLogin
  @GetMapping("/article/{id}")
  public String getArticle(Model model, @PathVariable int id) {

    Article article = articleService.viewArticle(id);
    model.addAttribute("article", new ArticleDTO(article));

    return "article";
  }


  @RequireLogin
  @GetMapping("/article")
  public String createArticle(Model model) {
    return "article_create";
  }

}
