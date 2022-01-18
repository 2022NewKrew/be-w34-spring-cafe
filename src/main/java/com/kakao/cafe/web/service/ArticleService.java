package com.kakao.cafe.web.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleBoard;
import com.kakao.cafe.domain.ArticlePage;
import com.kakao.cafe.domain.Delete;
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


  /**
   * 입력받은 페이지에 해당하는 게시판 리턴
   *
   * @param pageIndex 게시판 페이지
   * @return ArticleBoard
   */
  public ArticleBoard getArticleBoard(int pageIndex) {

    int totalSize = articleRepository.totalSize(Delete.NOT_DELETED);
    ArticleBoard articleBoard = ArticleBoard.createEmpty(totalSize, pageIndex);
    ArticlePage articlePage = articleRepository.findByOffset(
        articleBoard.offset(),
        articleBoard.getArticlesPerPage(),
        Delete.NOT_DELETED
    );
    articleBoard.setArticlePage(articlePage);

    return articleBoard;
  }


  /**
   * 게시 번호에 해당하는 게시물 반환
   *
   * @param id 게시 번호
   * @return Article
   */
  public Article viewArticle(Long id) {

    Article article = articleRepository.findById(id, Delete.NOT_DELETED)
        .orElseThrow(NoArticleException::new);

    article.addReadCount();
    articleRepository.updateReadCount(article);

    return article;
  }


  /**
   * 새 게시물 생성 후 생성된 게시물 반환
   *
   * @param article 새 게시물
   * @return Article
   */
  public Article createArticle(Article article) {
    return articleRepository.save(article);
  }


  /**
   * 게시 번호에 해당하는 게시물 삭제
   * 단, 유저에게만 보이지 않는 Soft delete 실행
   * Delete.SOFT_DELETED
   *
   * @param id 게시 번호
   * @throws NoArticleException 삭제할 게시물 없음.
   */
  public void softDeleteArticle(Long id) {

    int deleteCount = articleRepository.softDeleteById(id, Delete.SOFT_DELETED);

    if(deleteCount == 0) {
      throw new NoArticleException();
    }

  }

}
