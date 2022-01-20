package com.kakao.cafe.controller.api;

import com.kakao.cafe.controller.common.SessionLoginUser;
import com.kakao.cafe.reply.Reply;
import com.kakao.cafe.reply.ReplyService;
import com.kakao.cafe.reply.dto.ReplyCreateDto;
import com.kakao.cafe.reply.dto.ReplyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionApiController {

    private final SessionLoginUser sessionLoginUser;
    private final ReplyService replyService;

    @PostMapping("/{questionId}/answer")
    public ResponseEntity<List<ReplyDto>> saveReply(@RequestBody ReplyCreateDto replyCreateDto, @PathVariable Long questionId) {

        Reply reply = ReplyCreateDto.toEntity(replyCreateDto);
        reply.setQuestionId(questionId);
        reply.setMemberId(sessionLoginUser.getMemberId());
        reply.setWriter(sessionLoginUser.getUserId());

        try {
            replyService.save(reply);

        } catch (SQLException e) {
            log.info("reply save fail");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Long memberId = sessionLoginUser.getMemberId();

        List<ReplyDto> replyDto = ReplyDto.of(replyService.findAllAsQuestionId(questionId), memberId);

        return new ResponseEntity<>(replyDto, HttpStatus.OK);
    }

    @GetMapping("/{questionId}/answer")
    public ResponseEntity<List<ReplyDto>> getReply(@PathVariable Long questionId) {

        Long memberId = sessionLoginUser.getMemberId();

        List<ReplyDto> replyDto = ReplyDto.of(replyService.findAllAsQuestionId(questionId), memberId);

        return new ResponseEntity<>(replyDto, HttpStatus.OK);
    }
}
