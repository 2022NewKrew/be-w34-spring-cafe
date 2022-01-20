package com.kakao.cafe.adapter.in.presentation.reply;

import com.kakao.cafe.application.reply.dto.ReplyList;
import com.kakao.cafe.application.reply.port.in.GetRepliesUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReplyPrintController {

    private static final String VIEWS_ARTICLE_DETAIL = "article/show";

    private final GetRepliesUseCase getRepliesUseCase;

    public ReplyPrintController(GetRepliesUseCase getRepliesUseCase) {
        this.getRepliesUseCase = getRepliesUseCase;
    }

    @GetMapping("/articles/{articleId}/replies")
    public String displayRepliesInArticle(@PathVariable int articleId, Model model) {
        ReplyList replyList = getRepliesUseCase.getListOfRepliesOfTheArticle(articleId);
        int countOfReplies = replyList.getValue().size();
        model.addAttribute("countOfReplies", countOfReplies);
        model.addAttribute(
            "replies",
            replyList.getValue()
        );
        return VIEWS_ARTICLE_DETAIL;
    }
}
