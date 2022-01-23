package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ReplyDto;
import com.kakao.cafe.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;
    private Logger logger = LoggerFactory.getLogger(ReplyController.class);

    @PostMapping("/questions/{articleId}/replies")
    public String writeReply(ReplyDto replyDto, @PathVariable("articleId") Long articleId) {
        replyService.writeReply(replyDto);

        return String.format("redirect:/questions/%d", articleId);
    }

    @GetMapping("/questions/{articleId}/replies")
    public String replyList(Model model, @PathVariable("articleId") Long articleId) {
        List<ReplyDto> replyDtos = replyService.repliesByArticle(articleId);
        model.addAttribute("replies", replyDtos);
        model.addAttribute("count", replyDtos.size());

        return String.format("redirect:/questions/%d", articleId);
    }

    @DeleteMapping("/questions/{articleId}/replies/{id}")
    public String deleteReply(@PathVariable("articleId") Long articleId, @PathVariable("id") Long replyId) {
        replyService.deleteReply(replyId);

        return String.format("redirect:/questions/%d", articleId);
    }


}
