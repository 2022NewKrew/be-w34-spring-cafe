package com.kakao.cafe.module.repository.memory;

import com.kakao.cafe.module.model.domain.Article;
import com.kakao.cafe.module.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.kakao.cafe.module.model.dto.ArticleDtos.*;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImplMemory implements ArticleRepository {

    private final List<Article> articleList = new ArrayList<>();

    @Override
    public void addArticle(Article article) {
        articleList.add(article);
    }

    @Override
    public ArticleReadDto findArticleById(Long id) {
        return null;
    }

    @Override
    public List<ArticleListDto> findAllArticles() {
        return null;
    }
}
