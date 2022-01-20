package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.port.in.DeleteArticleUseCase;
import com.kakao.cafe.application.reply.dto.ReplyList;
import com.kakao.cafe.application.reply.port.in.GetRepliesUseCase;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleDeleteController {

    private final DeleteArticleUseCase deleteArticleUseCase;
    private final GetRepliesUseCase getRepliesUseCase;

    public ArticleDeleteController(DeleteArticleUseCase deleteArticleUseCase, GetRepliesUseCase getRepliesUseCase) {
        this.deleteArticleUseCase = deleteArticleUseCase;
        this.getRepliesUseCase = getRepliesUseCase;
    }

    @DeleteMapping("/articles/{id}/delete")
    public String deleteArticle(@PathVariable int id, HttpSession session) throws UnauthenticatedUserException {
        UserInfo sessionedUser = (UserInfo) session.getAttribute("sessionedUser");
        if (!isPossibleDeleteArticle(id, sessionedUser)) {
            throw new UnauthenticatedUserException("댓글을 삭제 할 수 없습니다.");
        }
        deleteArticleUseCase.delete(id);
        return "redirect:/";
    }

    private boolean isPossibleDeleteArticle(int id, UserInfo sessionedUser) {
        ReplyList replyList = getRepliesUseCase.getListOfRepliesOfTheArticle(id);

        return replyList.isEmpty() || replyList.containsReplyOf(sessionedUser.getUserId());
    }
}
