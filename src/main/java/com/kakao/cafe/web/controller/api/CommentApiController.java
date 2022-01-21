package com.kakao.cafe.web.controller.api;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.domain.Comments;
import com.kakao.cafe.utils.SessionUtils;
import com.kakao.cafe.web.common.EnableSession;
import com.kakao.cafe.web.controller.KakaoCafeApiController;
import com.kakao.cafe.web.dto.CommentDTO;
import com.kakao.cafe.web.dto.CommentsDTO;
import com.kakao.cafe.web.dto.ResponseDTO;
import com.kakao.cafe.web.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@KakaoCafeApiController
@EnableSession
public class CommentApiController {

  private static final Logger logger = LoggerFactory.getLogger(CommentApiController.class);
  private final CommentService commentService;

  public CommentApiController(CommentService commentService) {
    this.commentService = commentService;
  }


  /**
   * 해당 게시글에 댓글 생성 후 생성된 댓글 반환
   *
   * @param commentDTO 댓글 생성 요청
   * @return 생성된 댓글
   */
  @PostMapping("/comment")
  public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {

    Comment createdComment = commentService.createComment(commentDTO);

    return ResponseEntity.status(HttpStatus.OK)
        .body(new CommentDTO(createdComment));
  }


  /**
   * 해당 게시글의 댓글 전체 목록 반환
   *
   * @param articleId 게시 번호
   * @return 댓글 리스트
   */
  @GetMapping("/comments/{articleId}")
  public ResponseEntity<CommentsDTO> showComments(@PathVariable Long articleId) {

    Comments comments = commentService.getCommentsByArticle(articleId);

    return ResponseEntity.status(HttpStatus.OK)
        .body(new CommentsDTO(comments));
  }


  /**
   * 삭제 권한 체크 후 특정 댓글 삭제 후 결과 반환
   *
   * @param commentId 댓글 번호
   * @return 삭제 결과
   */
  @DeleteMapping("/comment/{commentId}")
  public ResponseEntity<ResponseDTO> deleteComment(@PathVariable Long commentId) {

    commentService.deleteCommentById(commentId);

    return ResponseEntity.status(HttpStatus.OK)
        .body(ResponseDTO.createSuccess());
  }


  /**
   * 좋아요 추가하고 댓글 반환
   *
   * @param commentId 댓글 번호
   * @return 댓글
   */
  @PostMapping("/comment/{commentId}/like")
  public ResponseEntity<CommentDTO> addLike(@PathVariable Long commentId) {

    Comment comment = commentService.addLike(commentId);

    return ResponseEntity.status(HttpStatus.OK)
        .body(new CommentDTO(comment));
  }


}
