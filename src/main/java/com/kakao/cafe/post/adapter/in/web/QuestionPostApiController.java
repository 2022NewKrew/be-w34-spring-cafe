package com.kakao.cafe.post.adapter.in.web;

import com.kakao.cafe.post.adapter.in.web.dto.request.QuestionPostUpdateRequest;
import com.kakao.cafe.post.adapter.in.web.dto.request.QuestionPostWriteRequest;
import com.kakao.cafe.post.adapter.in.web.dto.response.QuestionPostResponse;
import com.kakao.cafe.post.adapter.in.web.dto.response.QuestionPostUpdateResponse;
import com.kakao.cafe.post.adapter.in.web.dto.response.QuestionPostWriteResponse;
import com.kakao.cafe.post.application.dto.command.QuestionPostDeleteCommand;
import com.kakao.cafe.post.application.dto.command.QuestionPostUpdateCommand;
import com.kakao.cafe.post.application.dto.result.QuestionPostSaveResult;
import com.kakao.cafe.post.application.port.in.DeleteQuestionPostUseCase;
import com.kakao.cafe.post.application.port.in.EnrollQuestionPostUseCase;
import com.kakao.cafe.post.application.port.in.UpdateQuestionPostUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.validation.Valid;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class QuestionPostApiController {

    private final EnrollQuestionPostUseCase enrollQuestionPostUseCase;
    private final UpdateQuestionPostUseCase updateQuestionPostUseCase;
    private final DeleteQuestionPostUseCase deleteQuestionPostUseCase;

    @PostMapping("")
    public ResponseEntity<QuestionPostWriteResponse> write(@Valid @RequestBody QuestionPostWriteRequest request) {

        QuestionPostSaveResult result = enrollQuestionPostUseCase.enroll(request.toCommand());

        UriComponents uriComponents = MvcUriComponentsBuilder
                .fromMethodCall(on(QuestionPostApiController.class).write(request))
                .build();
        return ResponseEntity
                .created(uriComponents.toUri())
                .body(result.toResponse());
    }

    @PutMapping("/{post-id}")
    public ResponseEntity<QuestionPostResponse> update(
            @PathVariable(name = "post-id") Long postId,
            @Valid @RequestBody QuestionPostUpdateRequest updateRequest) {
        updateQuestionPostUseCase.updatePost(new QuestionPostUpdateCommand(
                postId,
                updateRequest.getTitle(),
                updateRequest.getContent()));
        return ResponseEntity
                .ok(new QuestionPostResponse("success"));
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity<QuestionPostResponse> delete(@PathVariable(name = "post-id") Long postId) {
        deleteQuestionPostUseCase.deletePost(new QuestionPostDeleteCommand(postId));
        return ResponseEntity
                .ok(new QuestionPostResponse("success"));
    }
}
