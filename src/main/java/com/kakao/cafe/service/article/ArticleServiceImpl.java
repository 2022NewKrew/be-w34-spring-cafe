package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.dto.article.ArticleReqDto;
import com.kakao.cafe.dto.article.ArticleResDto;
import com.kakao.cafe.repository.article.MemoryArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    private final MemoryArticleRepository articleRepository;

    @Override
    public void addArticle(ArticleReqDto articleReqDto) {
        Article article = Article.builder()
                .writer(articleReqDto.getWriter())
                .title(articleReqDto.getTitle())
                .contents(articleReqDto.getContents())
                .build();
        articleRepository.save(article);
    }

    @Override
    public List<ArticleResDto> findArticles() {
        return articleRepository.findAll().stream()
                .map(ArticleResDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleResDto findArticleById(Long articleId) {
        return new ArticleResDto(articleRepository.findByArticleId(articleId)
                .orElseThrow(() -> new NullPointerException("존재하지 않는 게시글입니다.")));
    }
}
