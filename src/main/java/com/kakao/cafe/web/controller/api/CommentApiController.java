package com.kakao.cafe.web.controller.api;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.domain.Comments;
import com.kakao.cafe.web.common.EnableSession;
import com.kakao.cafe.web.controller.KakaoCafeApiController;
import com.kakao.cafe.web.dto.CommentDTO;
import com.kakao.cafe.web.dto.CommentsDTO;
import com.kakao.cafe.web.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


  @PostMapping("/comment")
  public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {

    Comment createdComment = commentService.createComment(commentDTO);

    return ResponseEntity.status(HttpStatus.OK)
        .body(new CommentDTO(createdComment));
  }


  @GetMapping("/comments/{articleId}")
  public ResponseEntity<CommentsDTO> showComments(@PathVariable Long articleId) {

    Comments comments = commentService.getCommentsByArticle(articleId);

    return ResponseEntity.status(HttpStatus.OK)
        .body(new CommentsDTO(comments));
  }

}
