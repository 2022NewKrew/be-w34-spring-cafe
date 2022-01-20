package com.kakao.cafe.service;

import com.kakao.cafe.controller.dto.ArticleDto;
import com.kakao.cafe.controller.dto.ReplyDto;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.ReplyRepository;
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
    private final ReplyService replyService;

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

    public void create(String writer, ArticleDto articleDto) {
        Article article = articleDto.toEntity();
        articleRepository.save(article);
    }

    public ArticleDto findByIdForWriter(int id, String writer) {
        Article article = findByIdFromRepository(id);
        matchWriter(writer, article);
        return ArticleDto.from(article);
    }

    public void update(String writer, int articleId, ArticleDto articleDto) {
        Article article = findByIdFromRepository(articleId);
        matchWriter(writer, article);
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        article.setDate(LocalDateTime.now());
        articleRepository.update(article);
    }
    
    public void delete(int id, String writer) {
        Article article = findByIdFromRepository(id);
        matchWriter(writer, article);
        matchWriterOfReplies(writer, article);
        articleRepository.softDelete(id);
    }

    private Article findByIdFromRepository(int id) {
        Optional<Article> articleById = articleRepository.findById(id);
        return articleById.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물"));
    }

    private void matchWriter(String writer, Article article) {
        if ( !article.matchWriter(writer)) {
            throw new IllegalArgumentException("게시물 접근 권한이 없습니다.");
        }
    }

    private void matchWriterOfReplies(String writer, Article article) {
        if (!article.matchWriterOfReplies(writer)) {
            throw new IllegalArgumentException("댓글 접근 권한이 없습니다.");
        }
    }

}
