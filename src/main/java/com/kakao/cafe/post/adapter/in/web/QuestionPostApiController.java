package com.kakao.cafe.post.adapter.in.web;

import com.kakao.cafe.post.application.dto.result.QuestionPostSaveResult;
import com.kakao.cafe.post.application.port.in.EnrollQuestionPostUseCase;
import com.kakao.cafe.post.adapter.in.web.dto.QuestionPostWriteRequest;
import com.kakao.cafe.post.adapter.in.web.dto.QuestionPostWriteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.validation.Valid;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class QuestionPostApiController {

    private final EnrollQuestionPostUseCase enrollQuestionPostUseCase;

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
}
