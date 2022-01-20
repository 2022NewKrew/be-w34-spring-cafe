package com.kakao.cafe.controller.api;

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
    public ResponseEntity<SuccessResponse> makeComment(@PathVariable("qnaId") Integer qnaId, @RequestBody CommentDto.CreateCommentRequest createCommentRequest, HttpSession session) {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        commentService.createComment(qnaId, sessionedUser.getUserId(), createCommentRequest.getContents());

        return ResponseEntity.ok()
                .body(new SuccessResponse(200, true));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<SuccessResponse> makeComment(@PathVariable("qnaId") Integer qnaId, @PathVariable("commentId") Integer commentId, HttpSession session) throws Exception {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        commentService.deleteComment(commentId, sessionedUser.getUserId());

        return ResponseEntity.ok()
                .body(new SuccessResponse(200, true));
    }

}

