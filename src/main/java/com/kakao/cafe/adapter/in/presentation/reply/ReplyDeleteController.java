package com.kakao.cafe.adapter.in.presentation.reply;

import com.kakao.cafe.application.reply.port.in.DeleteReplyUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReplyDeleteController {

    private final DeleteReplyUseCase deleteReplyUseCase;

    public ReplyDeleteController(DeleteReplyUseCase deleteReplyUseCase) {
        this.deleteReplyUseCase = deleteReplyUseCase;
    }

    @DeleteMapping("/articles/{articleId}/replies/{id}")
    public String deleteReply(@PathVariable int articleId, @PathVariable int id) {
        deleteReplyUseCase.delete(id);
        return "redirect:/articles/" + articleId;
    }
}
