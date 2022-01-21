package com.kakao.cafe.web.service;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.domain.Comments;
import com.kakao.cafe.domain.Delete;
import com.kakao.cafe.exception.NoArticleException;
import com.kakao.cafe.web.dto.CommentDTO;
import com.kakao.cafe.web.repository.ArticleRepository;
import com.kakao.cafe.web.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  private static final Logger logger = LoggerFactory.getLogger(CommentService.class);
  private final CommentRepository commentRepository;
  private final ArticleRepository articleRepository;

  public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
    this.commentRepository = commentRepository;
    this.articleRepository = articleRepository;
  }


  /**
   * 게시 번호 하위에 댓글을 생성한다.
   *
   * @param commentDTO 새 입력 댓글
   * @return 생성 댓글
   */
  public Comment createComment(CommentDTO commentDTO) {

    Comment commentForSave = Comment.of(commentDTO);
    return commentRepository.save(commentForSave);
  }


  /**
   * 게시 번호의 유효성 체크 후 해당 게시물의 모든 댓글 목록을 반환한다.
   *
   * @param articleId 게시 번호
   * @return 댓글 목록
   * @throws NoArticleException 게시 번호 유효하지 않음
   */
  public Comments getCommentsByArticle(Long articleId) {

    articleRepository.findById(articleId, Delete.NOT_DELETED)
        .orElseThrow(NoArticleException::new);

    return commentRepository.findByArticleId(articleId, Delete.NOT_DELETED);
  }

}
