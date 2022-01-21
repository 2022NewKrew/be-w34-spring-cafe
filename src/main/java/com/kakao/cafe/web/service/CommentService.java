package com.kakao.cafe.web.service;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.domain.Comments;
import com.kakao.cafe.domain.Delete;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.NoArticleException;
import com.kakao.cafe.exception.NoAuthorityException;
import com.kakao.cafe.exception.NoCommentException;
import com.kakao.cafe.utils.SessionUtils;
import com.kakao.cafe.web.dto.CommentDTO;
import com.kakao.cafe.web.repository.ArticleRepository;
import com.kakao.cafe.web.repository.CommentLikeRepository;
import com.kakao.cafe.web.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  private static final Logger logger = LoggerFactory.getLogger(CommentService.class);
  private final CommentRepository commentRepository;
  private final ArticleRepository articleRepository;
  private final CommentLikeRepository commentLikeRepository;

  public CommentService(
      CommentRepository commentRepository,
      ArticleRepository articleRepository,
      CommentLikeRepository commentLikeRepository
  ) {
    this.commentRepository = commentRepository;
    this.articleRepository = articleRepository;
    this.commentLikeRepository = commentLikeRepository;
  }


  /**
   * 게시 번호 하위에 댓글을 생성한다.
   *
   * @param commentDTO 새 입력 댓글
   * @return 생성 댓글
   * @throws DataIntegrityViolationException 유저 또는 게시글 번호 유효하지 않음.
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


  /**
   * 댓글 번호에 해당하는 댓글 조회 후 삭제 상태를 업데이트 한다.
   *
   * @param commentId 삭제할 댓글
   */
  public void deleteCommentById(Long commentId) {

    Comment comment = commentRepository.findById(commentId, Delete.NOT_DELETED)
        .orElseThrow(NoCommentException::new);

    hasEditPermissions(comment);

    comment.delete(Delete.SOFT_DELETED);

    commentRepository.save(comment);

  }


  /**
   * 현재 댓글의 좋아요 개수를 추가하고 댓글 반환
   *
   * @param commentId 좋아요 추가할 댓글
   * @return 댓글
   * @throws NoAuthorityException 로그인 사용자만 가능
   * @throws NoCommentException 댓글이 없을 경우
   */
  public Comment addLike(Long commentId) {

    User user = SessionUtils.getLoginUser()
            .orElseThrow(NoAuthorityException::new);

    commentLikeRepository.save(commentId, user.getId());

    return commentRepository.findById(commentId, Delete.NOT_DELETED)
        .orElseThrow(NoCommentException::new);
  }


  /**
   * 현재 로그인 유저가 해당 댓글에 대한 수정 권한이 있는지 판단
   *
   * @param comment 수정할 댓글
   * @throws NoAuthorityException 권한 없음
   */
  public void hasEditPermissions(Comment comment) {
    User author = comment.getAuthor();

    if (!SessionUtils.isLoginUser(author)) {
      throw new NoAuthorityException();
    }
  }

}
