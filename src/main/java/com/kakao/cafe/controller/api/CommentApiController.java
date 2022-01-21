package com.kakao.cafe.controller.api;

import com.kakao.cafe.annotation.LoginUser;
import com.kakao.cafe.dto.CommentDto;
import com.kakao.cafe.dto.SuccessResponse;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/questions/{qnaId}/comments")
public class CommentApiController {
    private CommentService commentService;

    @Autowired
    public CommentApiController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> makeComment(@PathVariable("qnaId") Integer qnaId, @RequestBody CommentDto.CreateCommentRequest createCommentRequest,
                                                       @LoginUser UserDto.UserSessionDto loginUser) {
        commentService.createComment(qnaId, loginUser.getUserId(), createCommentRequest.getContents());

        return ResponseEntity.ok()
                .body(new SuccessResponse(200, true));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<SuccessResponse> makeComment(@PathVariable("qnaId") Integer qnaId, @PathVariable("commentId") Integer commentId,
                                                       @LoginUser UserDto.UserSessionDto loginUser) throws Exception {
        commentService.deleteComment(commentId, loginUser.getUserId());

        return ResponseEntity.ok()
                .body(new SuccessResponse(200, true));
    }

}

