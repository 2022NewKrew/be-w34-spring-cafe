package com.kakao.cafe.comment.adapter.in.web;

import com.kakao.cafe.comment.adapter.in.web.dto.request.LeaveCommentRequest;
import com.kakao.cafe.comment.adapter.in.web.dto.response.CommentResponse;
import com.kakao.cafe.comment.adapter.in.web.dto.response.LeaveCommentResponse;
import com.kakao.cafe.comment.application.dto.command.DeleteCommentCommand;
import com.kakao.cafe.comment.application.dto.command.GetCommentCommand;
import com.kakao.cafe.comment.application.dto.command.LeaveCommentCommand;
import com.kakao.cafe.comment.application.dto.result.GetCommentResult;
import com.kakao.cafe.comment.application.dto.result.LeaveCommentResult;
import com.kakao.cafe.comment.application.port.in.DeleteCommentUseCase;
import com.kakao.cafe.comment.application.port.in.GetCommentUseCase;
import com.kakao.cafe.comment.application.port.in.LeaveCommentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.validation.Valid;
import java.util.Objects;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;


@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentApiController {

    private final LeaveCommentUseCase leaveCommentUseCase;
    private final DeleteCommentUseCase deleteCommentUseCase;
    private final GetCommentUseCase getCommentUseCase;

    @PostMapping("")
    public ResponseEntity<LeaveCommentResponse> leave(
            @Valid @RequestBody LeaveCommentRequest request,
            @SessionAttribute(name = "user-id") Long userId) {

        LeaveCommentResult result = leaveCommentUseCase.leave(new LeaveCommentCommand(
                request.getContent(),
                userId,
                request.getQuestionPostId()
        ));

        UriComponents uriComponents = MvcUriComponentsBuilder
                .fromMethodCall(on(CommentApiController.class).leave(request, userId))
                .build();

        return ResponseEntity
                .created(uriComponents.toUri())
                .body(new LeaveCommentResponse(result.getCommentId()));
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity<CommentResponse> delete(
            @PathVariable(name = "comment-id") Long commentId,
            @SessionAttribute(name = "user-id") Long userId) {

        GetCommentResult getCommentResult = getCommentUseCase.get(new GetCommentCommand(commentId));
        if(Objects.equals(getCommentResult.getUserAccountId(), userId)) {
            deleteCommentUseCase.delete(new DeleteCommentCommand(commentId));
            return ResponseEntity.ok(new CommentResponse("댓글 삭제"));
        }

        return ResponseEntity.ok(new CommentResponse("본인의 댓글만 삭제 할 수 있습니다"));
    }
}
