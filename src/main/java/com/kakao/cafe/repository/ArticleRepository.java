package com.kakao.cafe.repository;

import com.kakao.cafe.dto.ArticleDto;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private final List<ArticleDto> articleList = new ArrayList<>();

    public void save(ArticleDto article) {
        articleList.add(article);
    }

    public List<ArticleDto> getAllArticles() {
        return articleList;
    }

    public ArticleDto findByIndex(int index) {
        if (index <= articleList.size() && index > 0)
            return articleList.get(index-1);
        return null;
    }
}
