package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.dto.UpdateRequest;
import com.kakao.cafe.application.article.port.in.UpdateArticleUseCase;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class ArticleUpdateController {

    private final UpdateArticleUseCase updateArticleUseCase;

    public ArticleUpdateController(UpdateArticleUseCase updateArticleUseCase) {
        this.updateArticleUseCase = updateArticleUseCase;
    }


    @PutMapping("/articles/{id}/form")
    public String update(@PathVariable int id, UpdateRequest updateRequest, HttpSession session)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, IllegalUserIdException, UnauthenticatedUserException {
        UserInfo sessionedUser = (UserInfo) session.getAttribute("sessionedUser");
        updateRequest.setId(id);
        updateRequest.setWriter(sessionedUser.getUserId());
        updateRequest.setWriter(sessionedUser.getName());
        updateArticleUseCase.updateArticle(updateRequest);
        return "redirect:/";
    }
}
