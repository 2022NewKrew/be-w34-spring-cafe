package com.kakao.cafe.view;

import com.kakao.cafe.dto.ArticleListDto;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleView {
    public void getIndexView(Model model, List<ArticleListDto> articleList) {
        List<ArticleListDto> sortedArticleList = new ArrayList<>(articleList);
        Collections.sort(sortedArticleList);
        model.addAttribute("articleList", sortedArticleList);
    }
}
