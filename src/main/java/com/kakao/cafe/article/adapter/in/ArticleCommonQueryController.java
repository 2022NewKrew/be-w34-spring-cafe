package com.kakao.cafe.article.adapter.in;

import com.kakao.cafe.article.application.port.in.ArticleCommonQueryUserCase;
import com.kakao.cafe.common.meta.URLPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ArticleCommonQueryController {
    private final ArticleCommonQueryUserCase articleCommonQueryUserCase;

    @GetMapping("/")
    public String showArticleList(Model model) {
        model.addAttribute("articles", articleCommonQueryUserCase.getArticleInventoryInfoList());
        return URLPath.INDEX.getPath();
    }

    @GetMapping("articles/{articleId}")
    public String showArticle(@PathVariable("articleId") Long articleId, Model model) {
        model.addAttribute("article", articleCommonQueryUserCase.findArticlePostInfo(articleId));
        return URLPath.SHOW_ARTICLE.getPath();
    }
}
