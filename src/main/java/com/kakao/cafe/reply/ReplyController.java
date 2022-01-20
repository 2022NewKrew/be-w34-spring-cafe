package com.kakao.cafe.reply;

import com.kakao.cafe.configures.web.ReplyRequestResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping(path = "/create")
    public String createReply(@ReplyRequestResolver ReplyRequest replyRequest) {
        replyService.createReply(Reply.builder()
                .userSeq(replyRequest.getUserSeq())
                .articleSeq(replyRequest.getArticleSeq())
                .writer(replyRequest.getWriter())
                .content(replyRequest.getContents())
                .build());
        return "redirect:/qna/show/" + replyRequest.getArticleSeq();
    }

    @DeleteMapping(path = "/delete/{seq}")
    public String deleteArticle(@PathVariable long seq, @ReplyRequestResolver ReplyRequest replyRequest) {
        replyService.deleteReply(Reply.builder()
                .seq(seq).userSeq(replyRequest.getUserSeq()).articleSeq(replyRequest.getArticleSeq()).build());
        return "redirect:/qna/show/" + replyRequest.getArticleSeq();
    }

}
