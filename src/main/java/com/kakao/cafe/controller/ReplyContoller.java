package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ReplyDto;
import com.kakao.cafe.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies")
@Slf4j
@RequiredArgsConstructor
public class ReplyContoller {
    private final ReplyService replyService;

    @GetMapping(value = "/article/{articleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDto>> getListByArticle(@PathVariable Long articleId) {
        log.debug("[Get] /replies/article/" + articleId);
        return new ResponseEntity<>(replyService.getList(articleId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> register(@RequestBody ReplyDto replyDto) {
        log.debug("[Post] /replies " + replyDto);
        Long replyId = replyService.register(replyDto);
        return new ResponseEntity<>(replyId, HttpStatus.OK);
    }
}
