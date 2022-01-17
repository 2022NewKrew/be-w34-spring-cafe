package com.kakao.cafe.service.article;

import com.kakao.cafe.service.article.mapper.ArticleDtoMapper;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.repository.article.ArticleRepository;
import com.kakao.cafe.service.article.dto.ArticleInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleDtoMapper articleDtoMapper;

    public List<ArticleInfo> getArticleAll() {
        List<Article> articles = articleRepository.findAll();
        return articleDtoMapper.toArticleInfoList(articles);
    }

    public ArticleInfo getArticleInfo(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return articleDtoMapper.toArticleInfo(article);
    }

    public Long writeArticle(String writer, String title, String contents) {
        Article article = Article.of(writer, title, contents);
        articleRepository.insertArticle(article);
        return article.getId();
    }
}
