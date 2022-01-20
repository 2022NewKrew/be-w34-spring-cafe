package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.port.in.DeleteArticleUseCase;
import com.kakao.cafe.application.reply.port.in.GetRepliesUseCase;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ArticleDeleteController {

    private final DeleteArticleUseCase deleteArticleUseCase;
    private final GetRepliesUseCase getRepliesUseCase;

    public ArticleDeleteController(DeleteArticleUseCase deleteArticleUseCase, GetRepliesUseCase getRepliesUseCase) {
        this.deleteArticleUseCase = deleteArticleUseCase;
        this.getRepliesUseCase = getRepliesUseCase;
    }

    @DeleteMapping("/articles/{id}/delete")
    public String deleteArticle(@PathVariable int id, @RequestParam String userId, @RequestAttribute UserInfo sessionedUser, RedirectAttributes redirectAttributes)
        throws UnauthenticatedUserException {
        deleteArticleUseCase.delete(id, userId, sessionedUser, getRepliesUseCase.getListOfRepliesOfTheArticle((id)));
        redirectAttributes.addAttribute("userId", userId);
        return "redirect:/articles/" + id + "/replies/delete";
    }
}
