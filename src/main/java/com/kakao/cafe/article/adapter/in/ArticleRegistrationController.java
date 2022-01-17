package com.kakao.cafe.article.adapter.in;

import com.kakao.cafe.article.application.port.in.ArticleRegistrationCommand;
import com.kakao.cafe.article.application.port.in.ArticleRegistrationUseCase;
import com.kakao.cafe.common.meta.URLPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ArticleRegistrationController {
    private final ArticleRegistrationUseCase articleRegistrationUseCase;

    @GetMapping("qna/form")
    public String qnaForm() {
        return URLPath.SHOW_ARTICLE_FORM.getPath();
    }

    @PostMapping("qna/form")
    public String createQuestion(@Valid @ModelAttribute ArticleRegistrationCommand articleRegistrationCommand, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return URLPath.SHOW_ARTICLE_FORM.getRedirectPath();
        }
        articleRegistrationUseCase.registerArticle(articleRegistrationCommand);
        return URLPath.INDEX.getPath();
    }
}
