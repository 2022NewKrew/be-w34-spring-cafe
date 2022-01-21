package com.kakao.cafe.interfaces.post.reply;

import com.kakao.cafe.application.ReplyService;
import com.kakao.cafe.domain.post.reply.Reply;
import com.kakao.cafe.interfaces.common.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts/{postId}/replies")
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping
    public List<Reply> replies(@PathVariable long postId) {
        return replyService.findByPostId(postId);
    }

    @PostMapping
    public ResponseEntity<String> write(@PathVariable long postId, ReplyDto replyDto) {
        replyService.write(replyDto, postId);
        return new ResponseEntity<>("New reply created", HttpStatus.CREATED);
    }
}
