package com.kakao.cafe.web.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleBoard;
import com.kakao.cafe.domain.ArticlePage;
import com.kakao.cafe.domain.Delete;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.NoArticleException;
import com.kakao.cafe.exception.NoAuthorityException;
import com.kakao.cafe.utils.SessionUtils;
import com.kakao.cafe.web.dto.ArticleDTO;
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
   * 게시 번호에 해당하는 게시물 반환 및 조회수 증가
   *
   * @param id 게시 번호
   * @return Article
   * @throws NoArticleException 해당하는 게시물이 없음.
   */
  public Article viewArticle(Long id) {

    Article article = findArticle(id);
    article.addReadCount();
    articleRepository.updateReadCount(article);

    return article;
  }


  /**
   * 게시 번호에 해당하는 게시물 반환
   *
   * @param id 게시 번호
   * @return Article
   * @throws NoArticleException 해당하는 게시물이 없음.
   */
  public Article findArticle(Long id) {
    return articleRepository.findById(id, Delete.NOT_DELETED)
        .orElseThrow(NoArticleException::new);
  }


  /**
   * 새 게시물 생성 후 생성된 게시물 반환
   *
   * @param articleDTO 새 게시물 요청 값
   * @return Article
   */
  public Article createArticle(ArticleDTO articleDTO) {
    Article article = Article.of(articleDTO);
    return articleRepository.save(article);
  }


  /**
   * 변경 권한이 있는지 확인 후 게시물 수정. 선 조회 후 항목 변경
   *
   * @param articleDTO 변경 게시물 요청 값
   * @return Article
   * @throws NoArticleException   게시 번호에 해당하는 게시물 미존재
   * @throws NoAuthorityException 게시 수정 권한 없는 로그인 상태
   */
  public Article modifyArticle(ArticleDTO articleDTO) {

    Article origin = articleRepository.findById(articleDTO.getId(), Delete.NOT_DELETED)
        .orElseThrow(NoArticleException::new);

    checkEditPermissions(origin);

    Article modified = Article.create(origin, articleDTO);
    articleRepository.save(modified);

    return modified;
  }


  /**
   * 삭제 권한이 있는지 확인 후, 게시 번호에 해당하는 게시물 삭제 단, 유저에게만 보이지 않는 Soft delete 실행 Delete.SOFT_DELETED
   *
   * @param id 게시 번호
   * @throws NoArticleException   삭제할 게시물 없음.
   * @throws NoAuthorityException 게시 삭제 권한 없는 로그인 상태
   */
  public void softDeleteArticle(Long id) {

    Article article = findArticle(id);

    checkEditPermissions(article);
    article.delete(Delete.SOFT_DELETED);

    articleRepository.save(article);
  }


  /**
   * 현재 로그인 유저가 해당 게시물에 대한 수정 권한이 있는지 판단
   *
   * @param article 수정할 게시물
   * @throws NoAuthorityException 권한 없음
   */
  public void checkEditPermissions(Article article) {
    User author = article.getAuthor();

    if(!SessionUtils.isLoginUser(author)) {
      throw new NoAuthorityException();
    }
  }

}
