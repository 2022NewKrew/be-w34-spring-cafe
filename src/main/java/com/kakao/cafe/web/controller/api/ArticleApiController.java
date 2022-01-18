package com.kakao.cafe.web.controller.api;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.web.common.EnableSession;
import com.kakao.cafe.web.controller.KakaoCafeApiController;
import com.kakao.cafe.web.dto.ArticleDTO;
import com.kakao.cafe.web.dto.ResponseDTO;
import com.kakao.cafe.web.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@KakaoCafeApiController
@EnableSession
public class ArticleApiController {

  private static final Logger logger = LoggerFactory.getLogger(ArticleApiController.class);
  private final ArticleService articleService;

  public ArticleApiController(ArticleService articleService) {
    this.articleService = articleService;
  }


  /**
   * 신규 글 작성 및 생성 및 생성된 글 반환
   *
   * @param articleDTO 글 정보
   * @return ResponseEntity<ArticleDTO>
   */
  @PostMapping("/article")
  public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO) {

    Article createdArticle = articleService.createArticle(articleDTO);

    return ResponseEntity.status(HttpStatus.OK)
        .header(HttpHeaders.LOCATION, "/article/" + createdArticle.getId())
        .body(new ArticleDTO(createdArticle));
  }


  /**
   * 해당 글 변경 후 결과 반환
   *
   * @param articleDTO 글 정보
   * @return ResponseEntity<ArticleDTO>
   */
  @PutMapping("/article")
  public ResponseEntity<ArticleDTO> modifyArticle(@RequestBody ArticleDTO articleDTO) {

    Article modifiedArticle = articleService.modifyArticle(articleDTO);

    return ResponseEntity.status(HttpStatus.OK)
        .header(HttpHeaders.LOCATION, "/article/" + modifiedArticle.getId())
        .body(new ArticleDTO(modifiedArticle));
  }


  /**
   * 해당 글 삭제 후 결과 반환
   *
   * @param id 게시 번호
   * @return ResponseEntity<ResponseDTO>
   */
  @DeleteMapping("/article/{id}")
  public ResponseEntity<ResponseDTO> deleteArticle(@PathVariable Long id) {

    articleService.softDeleteArticle(id);

    return ResponseEntity.status(HttpStatus.OK)
        .body(ResponseDTO.createSuccess());
  }

}
