package com.kakao.cafe.repository;

import com.kakao.cafe.dto.ArticleDto;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {
    private final List<ArticleDto> articleList = new ArrayList<>();

    public void save(ArticleDto article) {
        articleList.add(article);
    }
}
