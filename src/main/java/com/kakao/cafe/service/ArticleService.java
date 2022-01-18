package com.kakao.cafe.service;

import com.kakao.cafe.controller.ArticleDto;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.exception.InvalidSessionException;
import com.kakao.cafe.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<ArticleDto> findAll() {
        return articleRepository
                .findAll()
                .stream()
                .map(ArticleDto::from)
                .collect(Collectors.toList());
    }

    public ArticleDto findById(int id) {
        Article article = findByIdFromRepository(id);
        return ArticleDto.from(article);
    }

    public int create(String writer, ArticleDto articleDto) {
        Article article = articleDto.toEntity();
        validateSession(writer);
        return articleRepository.save(article);
    }

    public int update(String writer, int articleId, ArticleDto articleDto) {
        Article article = findByIdFromRepository(articleId);
        validateSession(writer);
        matchWriter(writer, article);
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        article.setDate(LocalDateTime.now());
        return articleRepository.update(article);
    }

    public ArticleDto findByIdForWriter(int id, String writer) {
        Article article = findByIdFromRepository(id);
        matchWriter(writer, article);
        return ArticleDto.from(article);
    }


    public ArticleDto delete(int id, String writer) {
        Article article = findByIdFromRepository(id);
        validateSession(writer);
        matchWriter(writer, article);
        articleRepository.delete(article);
        return ArticleDto.from(article);
    }

    private Article findByIdFromRepository(int id) {
        Optional<Article> articleById = articleRepository.findById(id);
        return articleById.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물"));
    }

    private void matchWriter(String writer, Article article) {
        if ( !article.matchWriter(writer)) {
            throw new IllegalArgumentException("다른 사람의 글을 수정할 수 없다.");
        }
    }

    private void validateSession(String writer) {
        if (writer.equals("null")) {
            throw new InvalidSessionException("로그인 하지 않은 사용자입니다.");
        }
    }

}
