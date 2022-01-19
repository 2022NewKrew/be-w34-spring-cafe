package com.kakao.cafe.article.adapter.in.web;

import com.kakao.cafe.article.application.port.in.ArticleCommonQueryUserCase;
import com.kakao.cafe.common.meta.URLPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ArticleCommonQueryController {
    private final ArticleCommonQueryUserCase articleCommonQueryUserCase;

    @GetMapping("/")
    public ModelAndView showArticleList(Model model) {
        return new ModelAndView(URLPath.HOME.getPath())
                .addObject("articles", articleCommonQueryUserCase.getArticleInventoryInfoList());
    }

    @GetMapping("articles/{articleId}")
    public String showArticle(@PathVariable("articleId") Long articleId, Model model) {
        model.addAttribute("article", articleCommonQueryUserCase.findArticlePostInfo(articleId));
        return URLPath.SHOW_ARTICLE.getPath();
    }
}
