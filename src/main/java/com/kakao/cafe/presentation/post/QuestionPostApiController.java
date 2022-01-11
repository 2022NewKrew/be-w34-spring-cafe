package com.kakao.cafe.presentation.post;

import com.kakao.cafe.application.post.QuestionPostService;
import com.kakao.cafe.domain.post.QuestionPost;
import com.kakao.cafe.presentation.dto.request.QuestionPostWriteRequest;
import com.kakao.cafe.presentation.dto.response.QuestionPostWriteResponse;
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

    private final QuestionPostService questionPostService;

    @PostMapping("")
    public ResponseEntity<QuestionPostWriteResponse> write(@Valid @RequestBody QuestionPostWriteRequest request) {

        QuestionPost saved = questionPostService.save(request.toCommand());

        UriComponents uriComponents = MvcUriComponentsBuilder
                .fromMethodCall(on(QuestionPostApiController.class).write(request))
                .build();
        return ResponseEntity
                .created(uriComponents.toUri())
                .body(new QuestionPostWriteResponse(saved.getQuestionPostId()));
    }
}
