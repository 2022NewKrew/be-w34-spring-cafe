package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.port.in.DeleteArticleUseCase;
import com.kakao.cafe.application.reply.port.in.DeleteReplyUseCase;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleReplyDeleteController {

    private final DeleteArticleUseCase deleteArticleUseCase;
    private final DeleteReplyUseCase deleteReplyUseCase;

    public ArticleReplyDeleteController(DeleteArticleUseCase deleteArticleUseCase, DeleteReplyUseCase deleteReplyUseCase) {
        this.deleteArticleUseCase = deleteArticleUseCase;
        this.deleteReplyUseCase = deleteReplyUseCase;
    }

    @DeleteMapping("/articles/{id}/delete")
    public String deleteArticle(@RequestParam String userId, @PathVariable int id, HttpSession session)
        throws UnauthenticatedUserException {
        UserInfo sessionedUser = (UserInfo) session.getAttribute("sessionedUser");
        if (!sessionedUser.getUserId().equals(userId)) {
            throw new UnauthenticatedUserException("인증 오류");
        }
        deleteArticleUseCase.delete(id);
        return "redirect:/";
    }

    @DeleteMapping("/articles/{articleId}/replies/{id}")
    public String deleteReply(@RequestParam String userId, @PathVariable int articleId, @PathVariable int id, HttpSession session)
        throws UnauthenticatedUserException {
        UserInfo sessionedUser = (UserInfo) session.getAttribute("sessionedUser");
        if (!sessionedUser.getUserId().equals(userId)) {
            throw new UnauthenticatedUserException("인증 오류");
        }
        deleteReplyUseCase.delete(id);
        return "redirect:/articles/" + articleId;
    }
}
