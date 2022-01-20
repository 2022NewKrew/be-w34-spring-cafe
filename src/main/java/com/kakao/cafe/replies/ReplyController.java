package com.kakao.cafe.replies;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ReplyController {
    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping("/{articleId}/replies")
    public void getRepliesByArticleIdAndStatusIsTrue(@PathVariable("articleId") Long articleId) {
        List<ReplyDto> replyDtoList = replyService.getAllReplyByArticleIdAndStatus(articleId, true);
    }
}
