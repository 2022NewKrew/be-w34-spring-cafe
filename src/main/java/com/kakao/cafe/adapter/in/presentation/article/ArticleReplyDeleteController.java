package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.port.in.DeleteArticleUseCase;
import com.kakao.cafe.application.reply.port.in.DeleteReplyUseCase;
import com.kakao.cafe.application.reply.port.in.GetRepliesUseCase;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.article.Reply;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleReplyDeleteController {

    private final DeleteArticleUseCase deleteArticleUseCase;
    private final DeleteReplyUseCase deleteReplyUseCase;
    private final GetRepliesUseCase getRepliesUseCase;

    public ArticleReplyDeleteController(DeleteArticleUseCase deleteArticleUseCase, DeleteReplyUseCase deleteReplyUseCase, GetRepliesUseCase getRepliesUseCase) {
        this.deleteArticleUseCase = deleteArticleUseCase;
        this.deleteReplyUseCase = deleteReplyUseCase;
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

    @DeleteMapping("/articles/{articleId}/replies/{id}")
    public String deleteReply(@PathVariable int articleId, @PathVariable int id) {
        deleteReplyUseCase.delete(id);
        return "redirect:/articles/" + articleId;
    }

    private boolean isPossibleDeleteArticle(int id, UserInfo sessionedUser) {
        List<Reply> replyList = getRepliesUseCase.getListOfRepliesOfTheArticle(id).getReplyList();
        if (replyList.size() == 0) {
            return true;
        }

        return replyList.stream().allMatch(r -> r.getUserId().equals(sessionedUser.getUserId()));
    }
}
