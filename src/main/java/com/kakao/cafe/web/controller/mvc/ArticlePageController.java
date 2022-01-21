package com.kakao.cafe.web.controller.mvc;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleBoard;
import com.kakao.cafe.exception.NoAuthorityException;
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


  /**
   * 특정 페이지의 게시판 조회 화면
   *
   * @param model     MVC
   * @param pageIndex 게시판 페이지 번호
   * @return articles.html
   */
  @GetMapping({"/articles", "/articles/{pageIndex}"})
  public String getArticleBoard(Model model, @PathVariable Optional<Integer> pageIndex) {

    int boardPageIndex = pageIndex.orElse(ArticleBoard.FIRST_PAGE_INDEX);
    ArticleBoard articleBoard = articleService.getArticleBoard(boardPageIndex);

    model.addAttribute("articleBoard", new ArticleBoardDTO(articleBoard));

    return "articles";
  }


  /**
   * 게시물 조회 화면
   *
   * @param model MVC
   * @param id    게시 번호
   * @return article.html
   */
  @RequireLogin
  @GetMapping("/article/{id}")
  public String showArticle(Model model, @PathVariable Long id) {

    Article article = articleService.viewArticle(id);

    boolean editPermissions = true;

    try{
      articleService.checkEditPermissions(article);
    }catch(NoAuthorityException e) {
      editPermissions = false;
    }

    model.addAttribute("article", new ArticleDTO(article));
    model.addAttribute("editPermissions", editPermissions);

    return "article";
  }


  /**
   * 게시물 생성 화면
   *
   * @param model MVC
   * @return article_create.html
   */
  @RequireLogin
  @GetMapping("/article")
  public String createArticle(Model model) {
    return "article_create";
  }


  /**
   * 게시물 변경 화면, 변경은 게시글 작성자와 로그인 유저가 같아야 한다.
   *
   * @param model MVC
   * @return article_modify.html
   * @throws NoAuthorityException 수정 권한 없음
   */
  @RequireLogin
  @GetMapping("/article/{id}/modify")
  public String modifyArticle(Model model, @PathVariable Long id) {

    Article article = articleService.findArticle(id);

    articleService.checkEditPermissions(article);

    model.addAttribute("article", new ArticleDTO(article));

    return "article_modify";
  }

}
