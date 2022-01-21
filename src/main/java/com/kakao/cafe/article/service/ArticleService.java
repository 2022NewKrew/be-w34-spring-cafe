package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.article.web.dto.ArticleModifyDto;
import com.kakao.cafe.article.web.dto.ArticleSaveDto;
import com.kakao.cafe.article.web.dto.ArticleShowDto;
import com.kakao.cafe.reply.service.ReplyService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ReplyService replyService;

    public void addArticle(ArticleSaveDto articleSaveDto) {
        Article article = Article.builder()
            .writer(articleSaveDto.getWriter())
            .title(articleSaveDto.getTitle())
            .contents(articleSaveDto.getContents())
            .build();
        articleRepository.save(article);
    }

    public List<ArticleShowDto> findAllArticle() {
        List<Article> articleList = articleRepository.findAll();
        return articleList.stream()
            .map(this::createArticleShowDto)
            .collect(Collectors.toList());
    }

    public ArticleShowDto findArticle(Long id) {
        return createArticleShowDto(articleRepository.findById(id));
    }

    private ArticleShowDto createArticleShowDto(Article article) {
        return ArticleShowDto.builder()
            .index(article.getId())
            .writer(article.getWriter())
            .title(article.getTitle())
            .contents(article.getContents())
            .replies(replyService.findReplyByArticle(article.getId().intValue()))
            .build();
    }

    public void modifyArticle(Long id, ArticleModifyDto articleModifyDto) {
        Article article = Article.builder()
            .title(articleModifyDto.getTitle())
            .contents(articleModifyDto.getContents())
            .build();
        articleRepository.update(id, article);
    }

    public void removeArticle(Long id) {
        articleRepository.delete(id);
    }

    public String findArticleWriter(Long id) {
        return findArticle(id).getWriter();
    }
}
