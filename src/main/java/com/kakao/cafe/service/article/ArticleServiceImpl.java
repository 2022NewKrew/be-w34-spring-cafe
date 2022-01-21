package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.dto.article.ArticleRequest;
import com.kakao.cafe.dto.article.ArticleResponse;
import com.kakao.cafe.dto.article.ArticleUpdateDto;
import com.kakao.cafe.repository.article.ArticleRepository;
import com.kakao.cafe.util.exception.ArticleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(@Qualifier("jdbcArticleRepository") ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    @Override
    public void addArticle(ArticleRequest articleRequest) {
        Article article = Article.builder()
                .writer(articleRequest.getWriter())
                .title(articleRequest.getTitle())
                .contents(articleRequest.getContents())
                .deleted(false)
                .build();
        articleRepository.insert(article);
    }

    @Override
    public List<ArticleResponse> findArticles() {
        return articleRepository.selectAll().stream()
                .map(ArticleResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleResponse findArticleById(Long articleId) {
        return new ArticleResponse(articleRepository.selectByArticleId(articleId)
                .orElseThrow(() -> new ArticleNotFoundException("존재하지 않는 게시글입니다.")));
    }

    @Override
    public void modifyArticle(ArticleUpdateDto articleUpdateDto, Boolean removal) {
        Article article = articleRepository.selectByArticleId(articleUpdateDto.getArticleId())
                .orElseThrow(() -> new ArticleNotFoundException("존재하지 않는 게시글입니다."));

        articleRepository.update(Article.builder()
                .articleId(article.getArticleId())
                .writer(articleUpdateDto.getWriter())
                .title(articleUpdateDto.getTitle())
                .contents(articleUpdateDto.getContents())
                .deleted(removal)
                .build());
    }
}
